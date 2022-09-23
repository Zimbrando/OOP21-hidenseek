package hidenseek;

import java.util.List;

interface BasicController {

    void attach(View v);
    
    void detach(View v);
    
    List<View> getAttachedViews();
}
