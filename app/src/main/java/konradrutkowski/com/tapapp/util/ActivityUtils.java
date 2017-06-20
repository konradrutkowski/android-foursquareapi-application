package konradrutkowski.com.tapapp.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

/**
 * Created by konrad on 20.06.2017.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int id, boolean addToBackstack, String TAG) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id, fragment);
        if (addToBackstack && TAG != null && TextUtils.isEmpty(TAG))
            fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }
}
