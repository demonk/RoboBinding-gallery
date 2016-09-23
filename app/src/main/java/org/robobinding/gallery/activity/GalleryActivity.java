package org.robobinding.gallery.activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.common.collect.Maps;

import org.robobinding.gallery.R;
import org.robobinding.gallery.presentationmodel.GalleryPresentationModel;

import java.util.Map;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class GalleryActivity extends AbstractActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	Map<String, Class<? extends Activity>> demoActivityMappings = Maps.newLinkedHashMap();
	demoActivityMappings.put("View", ViewActivity.class);
	demoActivityMappings.put("EditText", EditTextActivity.class);
	demoActivityMappings.put("AdapterView", AdapterViewActivity.class);
	demoActivityMappings.put("ListView", ListViewActivity.class);
	demoActivityMappings.put("RecyclerView", RecyclerViewActivity.class);
	demoActivityMappings.put("Custom Component", CustomComponentActivity.class);
	demoActivityMappings.put("TypedCursor", TypedCursorActivity.class);
	demoActivityMappings.put("Fragment & ViewPager", ListFragmentDemoActivity.class);
	demoActivityMappings.put("Options Menu", OptionsMenuActivity.class);
	demoActivityMappings.put("Context Menu", ContextMenuDemoActivity.class);//上下文菜单 ，就是长按菜单
	demoActivityMappings.put("Contextual Action Mode", ContextualActionModeActivity.class);

	GalleryPresentationModel presentationModel = new GalleryPresentationModel(this, demoActivityMappings);
	initializeContentView(R.layout.activity_gallery, presentationModel);
    }
}