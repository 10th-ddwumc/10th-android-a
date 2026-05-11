package ddwu.umc.chapter03.ui.profile;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0013B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u001c\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\f\u001a\u00020\bH\u0016J\u001c\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0014\u0010\u0011\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lddwu/umc/chapter03/ui/profile/FollowingAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lddwu/umc/chapter03/ui/profile/FollowingAdapter$FollowingViewHolder;", "userList", "", "Lddwu/umc/chapter03/data/model/UserData;", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateData", "newList", "FollowingViewHolder", "app_debug"})
public final class FollowingAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<ddwu.umc.chapter03.ui.profile.FollowingAdapter.FollowingViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<ddwu.umc.chapter03.data.model.UserData> userList;
    
    public FollowingAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<ddwu.umc.chapter03.data.model.UserData> userList) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public ddwu.umc.chapter03.ui.profile.FollowingAdapter.FollowingViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    ddwu.umc.chapter03.ui.profile.FollowingAdapter.FollowingViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateData(@org.jetbrains.annotations.NotNull()
    java.util.List<ddwu.umc.chapter03.data.model.UserData> newList) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lddwu/umc/chapter03/ui/profile/FollowingAdapter$FollowingViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lddwu/umc/chapter03/databinding/ItemFollowingBinding;", "(Lddwu/umc/chapter03/ui/profile/FollowingAdapter;Lddwu/umc/chapter03/databinding/ItemFollowingBinding;)V", "bind", "", "user", "Lddwu/umc/chapter03/data/model/UserData;", "app_debug"})
    public final class FollowingViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final ddwu.umc.chapter03.databinding.ItemFollowingBinding binding = null;
        
        public FollowingViewHolder(@org.jetbrains.annotations.NotNull()
        ddwu.umc.chapter03.databinding.ItemFollowingBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        ddwu.umc.chapter03.data.model.UserData user) {
        }
    }
}