package ddwu.umc.chapter03.ui.profile;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ddwu.umc.chapter03.domain.repository.UserRepository;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class UserViewModel_Factory implements Factory<UserViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  public UserViewModel_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public UserViewModel get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static UserViewModel_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new UserViewModel_Factory(userRepositoryProvider);
  }

  public static UserViewModel newInstance(UserRepository userRepository) {
    return new UserViewModel(userRepository);
  }
}
