package io.mochadwi.presentation.presenter;

import io.mochadwi.domain.User;
import io.mochadwi.domain.exception.DefaultErrorBundle;
import io.mochadwi.domain.exception.ErrorBundle;
import io.mochadwi.domain.interactor.DefaultObserver;
import io.mochadwi.domain.interactor.GetUserList;
import io.mochadwi.presentation.exception.ErrorMessageFactory;
import io.mochadwi.presentation.internal.di.PerActivity;
import io.mochadwi.presentation.mapper.UserModelDataMapper;
import io.mochadwi.presentation.model.UserModel;
import io.mochadwi.presentation.view.UserListView;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserListPresenter implements Presenter {

    private final GetUserList getUserListUseCase;

    private final UserModelDataMapper userModelDataMapper;

    private UserListView viewListView;

    @Inject
    public UserListPresenter(GetUserList getUserListUserCase,
        UserModelDataMapper userModelDataMapper) {
        this.getUserListUseCase = getUserListUserCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull UserListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getUserListUseCase.dispose();
        this.viewListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadUserList();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void getUserList() {
        this.getUserListUseCase.execute(new UserListObserver(), null);
    }

    public void onUserClicked(UserModel userModel) {
        this.viewListView.viewUser(userModel);
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
            errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showUsersCollectionInView(Collection<User> usersCollection) {
        final Collection<UserModel> userModelsCollection =
            this.userModelDataMapper.transform(usersCollection);
        this.viewListView.renderUserList(userModelsCollection);
    }

    private final class UserListObserver extends DefaultObserver<List<User>> {

        @Override
        public void onNext(List<User> users) {
            UserListPresenter.this.showUsersCollectionInView(users);
        }

        @Override
        public void onComplete() {
            UserListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserListPresenter.this.showViewRetry();
        }
    }
}
