package ifrs.pw3.trabalhowebiii.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ifrs.pw3.trabalhowebiii.R;

public class MyCustomDialog extends DialogFragment {

    private static final String TAG = "MyCustomDialog";


    private TextView mActionOk;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_my_custom, container, false);

        mActionOk = view.findViewById(R.id.action_ok);

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "onClick: capturing input.");


                getDialog().dismiss();
            }
        });

        return view;
    }

}
