package kr.edcan.cardline.activity;

import android.Manifest;
import android.net.Network;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;
import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityEditorPageSettingsBinding;
import kr.edcan.cardline.models.User;
import kr.edcan.cardline.utils.CredentialsManager;
import kr.edcan.cardline.utils.ImageSingleTon;
import kr.edcan.cardline.utils.NetworkHelper;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPageSettingsActivity extends BaseActivity {

    ActivityEditorPageSettingsBinding binding;

    String imagePath = "";

    @Override
    protected void setDefault() {
        binding = (ActivityEditorPageSettingsBinding) baseBinding;
        binding.profileImageShow.setImageUrl(
                CredentialsManager.getInstance().getActiveUser().second.getProfile_img(),
                ImageSingleTon.getInstance(this).getImageLoader()
        );
        binding.profileTextInput.setText(CredentialsManager.getInstance().getActiveUser().second.getProfile());
        binding.nameInput.setText(CredentialsManager.getInstance().getActiveUser().second.getName());

        binding.profileImageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImage();
            }
        });
        binding.comfirmUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.nameInput.getText().toString().trim().equals("") || binding.profileTextInput.getText().toString().trim().equals("")) {
                    Toast.makeText(EditorPageSettingsActivity.this, "빈칸 없이 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    NetworkHelper.getNetworkInstance().updateSelfInfo(
                            CredentialsManager.getInstance().getActiveUser().second.getToken(),
                            binding.nameInput.getText().toString().trim(),
                            binding.profileTextInput.getText().toString().trim()
                    ).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            switch (response.code()) {
                                case 200:
                                    Log.e("asdf", "UpdateSelfInfo");
                                    if(imagePath.equals("")) return;
                                    RequestBody imageBody = RequestBody.create(MediaType.parse("image/png"), new File(imagePath));
                                    RequestBody token = RequestBody.create(MediaType.parse("text/plain"), CredentialsManager.getInstance().getActiveUser().second.getToken());
                                    NetworkHelper.getNetworkInstance().updateSelfInfoPhoto(imageBody, token).enqueue(new Callback<User>() {
                                        @Override
                                        public void onResponse(Call<User> call, Response<User> response) {
                                            Log.e("asdf", response.code() + "");
                                            switch (response.code()) {
                                                case 200:
                                                    Log.e("asdf", "UpdateSelfInfoPhoto");
                                                    CredentialsManager.getInstance().updateUserInfo(response.body());
                                                    Toast.makeText(EditorPageSettingsActivity.this, "정상적으로 반영되었습니다.", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    break;
                                                default:
                                                    Log.e("asdf", response.code() + "");
                                                    break;
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<User> call, Throwable t) {
                                            Log.e("asdf", "UpdateSelfInfoPhoto fail");
                                            Log.e("asdf", t.getLocalizedMessage());
                                        }
                                    });
                                    break;
                                default:
                                    Log.e("asdf", response.code() + "");
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("asdf", "UpdateSelfInfo fail");
                            Log.e("asdf", t.getLocalizedMessage());
                        }
                    });
                }
            }
        });
    }

    public void setImage() {
        new TedPermission(this)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .setDeniedMessage("권한이 허용되지 않아 프로필 이미지를 수정할 수 없습니다.")
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        new TedBottomPicker.Builder(EditorPageSettingsActivity.this)
                                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                                    @Override
                                    public void onImageSelected(Uri uri) {
                                        binding.profileImageShow.setImageURI(uri);
                                        imagePath = uri.getEncodedPath();
                                    }
                                }).create().show(getSupportFragmentManager());

                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Log.e("asdf", "Denied");
                    }
                }).check();

    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_editor_page_settings;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
