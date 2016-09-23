package org.robobinding.gallery.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.robobinding.ViewBinder;
import org.robobinding.gallery.R;
import org.robobinding.gallery.model.MemoryProductStore;
import org.robobinding.gallery.presentationmodel.ListFragmentDemoPresentationModel;

/**
 * @author Cheng Wei
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class ListFragmentDemo extends AbstractFragment {
    private ListFragmentDemoPresentationModel presentationModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MemoryProductStore productStore = MemoryProductStore.getInstance();
        productStore.reset();
        presentationModel = new ListFragmentDemoPresentationModel(getActivity(), productStore.getAll());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewBinder viewBinder = createViewBinder();
        //获取经过绑定的view，本质上还是需要通过XML绑定
        return viewBinder.inflateAndBindWithoutAttachingToRoot(R.layout.fragment_list_demo, presentationModel, container);
    }
}
