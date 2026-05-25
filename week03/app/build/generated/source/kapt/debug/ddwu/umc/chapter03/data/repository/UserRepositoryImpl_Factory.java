package ddwu.umc.chapter03.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ddwu.umc.chapter03.data.api.ReqResService;
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
public final class UserRepositoryImpl_Factory implements Factory<UserRepositoryImpl> {
  private final Provider<ReqResService> reqResServiceProvider;

  public UserRepositoryImpl_Factory(Provider<ReqResService> reqResServiceProvider) {
    this.reqResServiceProvider = reqResServiceProvider;
  }

  @Override
  public UserRepositoryImpl get() {
    return newInstance(reqResServiceProvider.get());
  }

  public static UserRepositoryImpl_Factory create(Provider<ReqResService> reqResServiceProvider) {
    return new UserRepositoryImpl_Factory(reqResServiceProvider);
  }

  public static UserRepositoryImpl newInstance(ReqResService reqResService) {
    return new UserRepositoryImpl(reqResService);
  }
}
