package ddwu.umc.chapter03.ui.shop;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0014\u0010\u0013\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lddwu/umc/chapter03/ui/shop/ShopProductAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lddwu/umc/chapter03/ui/shop/ShopViewHolder;", "productList", "", "Lddwu/umc/chapter03/data/model/ProductData;", "onHeartClick", "Lkotlin/Function1;", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateData", "newData", "app_debug"})
public final class ShopProductAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<ddwu.umc.chapter03.ui.shop.ShopViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<ddwu.umc.chapter03.data.model.ProductData> productList;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<ddwu.umc.chapter03.data.model.ProductData, kotlin.Unit> onHeartClick = null;
    
    public ShopProductAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<ddwu.umc.chapter03.data.model.ProductData> productList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super ddwu.umc.chapter03.data.model.ProductData, kotlin.Unit> onHeartClick) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public ddwu.umc.chapter03.ui.shop.ShopViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    ddwu.umc.chapter03.ui.shop.ShopViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateData(@org.jetbrains.annotations.NotNull()
    java.util.List<ddwu.umc.chapter03.data.model.ProductData> newData) {
    }
}