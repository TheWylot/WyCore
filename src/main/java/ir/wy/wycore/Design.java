package ir.wy.wycore;

import com.cryptomorin.xseries.XMaterial;
import lombok.NoArgsConstructor;
import java.util.Collections;
import java.util.Map;

@NoArgsConstructor
public class Design {

    public Item filler = new Item(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, " ", Collections.emptyList());
    public Map<Integer, Item> items;

    public Design(Map<Integer, Item> items) {
        this.items = items;
    }

    public Design(Map<Integer, Item> items, Item filler) {
        this.items = items;
        this.filler = filler;
    }
}
