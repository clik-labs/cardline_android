package kr.edcan.cardline.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kr.edcan.cardline.R;
import kr.edcan.cardline.activity.AuthActivity;
import kr.edcan.cardline.databinding.FragmentRegisterBinding;
import kr.edcan.cardline.models.User;
import kr.edcan.cardline.utils.CredentialsManager;
import kr.edcan.cardline.utils.NetworkHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-07-18.
 */

public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.emailInput.getText().toString().trim().equals("") || binding.passwordInput.getText().toString().trim().equals("") || binding.passwordReInput.getText().toString().trim().equals("") || binding.nameInput.getText().toString().trim().equals(""))
                    Toast.makeText(getContext(), "빈칸 없이 입력해주세요", Toast.LENGTH_SHORT).show();
                else if (binding.passwordInput.getText().toString().trim().equals(binding.passwordReInput.getText().toString().trim())) {
                    NetworkHelper.getNetworkInstance().registerLocal(
                            binding.emailInput.getText().toString().trim(),
                            binding.nameInput.getText().toString().trim(),
                            binding.passwordInput.getText().toString().trim()
                    ).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            switch (response.code()) {
                                case 200:
                                    Toast.makeText(getContext(), "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                                    ((AuthActivity) getActivity()).switchPage(1);
                                    break;
                                case 409:
                                    Toast.makeText(getContext(), "이미 존재하는 이메일입니다!", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("asdf", t.getLocalizedMessage());
                        }
                    });
                } else Toast.makeText(getContext(), "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

}
