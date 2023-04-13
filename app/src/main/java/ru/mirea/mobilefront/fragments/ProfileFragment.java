package ru.mirea.mobilefront.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.android.material.button.MaterialButton;

import ru.mirea.mobilefront.MainActivity;
import ru.mirea.mobilefront.R;
import ru.mirea.mobilefront.dto.CardDto;
import ru.mirea.mobilefront.service.AuthService;
import ru.mirea.mobilefront.service.CardService;
import ru.mirea.mobilefront.service.UserService;
import ru.mirea.mobilefront.service.UserSession;

public class ProfileFragment extends Fragment {

    boolean phoneButtonPressed = false;
    boolean addressButtonPressed = false;

    MutableLiveData<String> tokenData = AuthService.getLiveData();
    MutableLiveData<UserSession> userSession = UserSession.getUserSession();

    UserService userService = new UserService();

    MaterialButton imageButton;
    TextView username;

    TextView phoneText;
    TextView addressText;

    EditText editPhoneText;
    EditText editAddressText;

    MaterialButton editAddressButton;
    MaterialButton editPhoneButton;

    TextView bonus;
    TextView level;
    ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return (ViewGroup)inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

}


