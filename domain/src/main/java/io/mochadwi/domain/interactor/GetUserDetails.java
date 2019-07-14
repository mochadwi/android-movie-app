package io.mochadwi.domain.interactor;

import io.mochadwi.domain.User;
import io.mochadwi.domain.executor.PostExecutionThread;
import io.mochadwi.domain.executor.ThreadExecutor;
import io.mochadwi.domain.repository.UserRepository;
import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 */
public class GetUserDetails extends UseCase<User, GetUserDetails.Params> {

  private final UserRepository userRepository;

  @Inject
  GetUserDetails(UserRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override Observable<User> buildUseCaseObservable(Params params) {
    Preconditions.checkNotNull(params);
    return this.userRepository.user(params.userId);
  }

  public static final class Params {

    private final int userId;

    private Params(int userId) {
      this.userId = userId;
    }

    public static Params forUser(int userId) {
      return new Params(userId);
    }
  }
}
