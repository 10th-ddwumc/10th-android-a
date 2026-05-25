package ddwu.umc.chapter03.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lddwu/umc/chapter03/data/repository/UserRepositoryImpl;", "Lddwu/umc/chapter03/domain/repository/UserRepository;", "reqResService", "Lddwu/umc/chapter03/data/api/ReqResService;", "(Lddwu/umc/chapter03/data/api/ReqResService;)V", "getUser", "Lretrofit2/Response;", "Lddwu/umc/chapter03/data/model/SingleUserResponse;", "apiKey", "", "userID", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserList", "Lddwu/umc/chapter03/data/model/UserListResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserRepositoryImpl implements ddwu.umc.chapter03.domain.repository.UserRepository {
    @org.jetbrains.annotations.NotNull()
    private final ddwu.umc.chapter03.data.api.ReqResService reqResService = null;
    
    @javax.inject.Inject()
    public UserRepositoryImpl(@org.jetbrains.annotations.NotNull()
    ddwu.umc.chapter03.data.api.ReqResService reqResService) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getUser(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, int userID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<ddwu.umc.chapter03.data.model.SingleUserResponse>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getUserList(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<ddwu.umc.chapter03.data.model.UserListResponse>> $completion) {
        return null;
    }
}