package ddwu.umc.chapter03.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lddwu/umc/chapter03/ui/profile/UserViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepository", "Lddwu/umc/chapter03/domain/repository/UserRepository;", "(Lddwu/umc/chapter03/domain/repository/UserRepository;)V", "API_KEY", "", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lddwu/umc/chapter03/ui/profile/UserUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchSingleUser", "", "userId", "", "fetchUserList", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class UserViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final ddwu.umc.chapter03.domain.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<ddwu.umc.chapter03.ui.profile.UserUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<ddwu.umc.chapter03.ui.profile.UserUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String API_KEY = "reqres_def8c0b458de43c6a6d1a13bbe845d90";
    
    @javax.inject.Inject()
    public UserViewModel(@org.jetbrains.annotations.NotNull()
    ddwu.umc.chapter03.domain.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<ddwu.umc.chapter03.ui.profile.UserUiState> getUiState() {
        return null;
    }
    
    public final void fetchSingleUser(int userId) {
    }
    
    public final void fetchUserList() {
    }
}