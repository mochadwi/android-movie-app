package io.mochadwi.presentation.view.fragment;

import android.app.Fragment;
import android.widget.Toast;
import io.mochadwi.presentation.internal.di.HasComponent;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment extends Fragment {
  /**
   * Shows a {@link android.widget.Toast} message.
   *
   * @param message An string representing a message to be shown.
   */
  protected void showToastMessage(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  /**
   * Gets a component for dependency injection by its type.
   */
  @SuppressWarnings("unchecked")
  protected <C> C getComponent(Class<C> componentType) {
    return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
  }
}
