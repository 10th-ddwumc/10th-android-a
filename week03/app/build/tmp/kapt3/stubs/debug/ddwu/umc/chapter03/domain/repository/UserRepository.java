package ddwu.umc.chapter03.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lddwu/umc/chapter03/domain/repository/UserRepository;", "", "getUser", "Lretrofit2/Response;", "Lddwu/umc/chapter03/data/model/SingleUserResponse;", "apiKey", "", "userId", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserList", "Lddwu/umc/chapter03/data/model/UserListResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface UserRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUser(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<ddwu.umc.chapter03.data.model.SingleUserResponse>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserList(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<ddwu.umc.chapter03.data.model.UserListResponse>> $completion);
}