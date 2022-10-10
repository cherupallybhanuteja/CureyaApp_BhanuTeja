package com.example.cureya_chatbot.ui.chat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cureya_chatbot.ChatActivity;
import com.example.cureya_chatbot.ChatAdapter;
import com.example.cureya_chatbot.Message;
import com.example.cureya_chatbot.R;
import com.example.cureya_chatbot.SendMessageInBg;
import com.example.cureya_chatbot.loadingAnim.BallView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.api.client.util.Lists;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class ChatFragment extends Fragment implements com.example.cureya_chatbot.BotReply {

    private ChatViewModel chatViewModel;

    RecyclerView chatView;
    ChatAdapter chatAdapter;
    ArrayList<Message> messageList = new ArrayList<>();
    TextInputEditText editMessage;
    ImageButton btnSend;
    BallView ballView;
    private TextToSpeech tts;
    ImageView imgMic;
    //dialogFlow
    private SessionsClient sessionsClient;
    private SessionName sessionName;
    private String uuid = UUID.randomUUID().toString();
    private String TAG = "projectId";
    private Object BotReply;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//
//        chatViewModel =
//                new ViewModelProvider(this).get(ChatViewModel.class);


        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);

        chatView = root.findViewById(R.id.chatView);
        editMessage = root.findViewById(R.id.editMessage);
        btnSend = root.findViewById(R.id.btnSend);
        ballView = root.findViewById(R.id.typing_indicator);
        imgMic =root.findViewById(R.id.imgMic);

        chatAdapter = new ChatAdapter(messageList);
        chatView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatView.setAdapter(chatAdapter);



        imgMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listen();
            }
        });



        tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(new Locale("Eng","IND"));

                    tts.setSpeechRate(1.0f);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    tts.setPitch(1.0f);
//                            speak("");
                } else {

                    Log.e("TTS", "Initialization Failed!");
                }
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String message = editMessage.getText().toString();
                if (!message.isEmpty()) {
                    ;
                    messageList.add(new com.example.cureya_chatbot.Message(message,false));
                    editMessage.setText("");

                    sendMessageToBot(message);
                    ballView.setVisibility(View.VISIBLE);
                    Objects.requireNonNull(chatView.getAdapter()).notifyDataSetChanged();
                    Objects.requireNonNull(chatView.getLayoutManager())
                            .scrollToPosition(messageList.size() - 1);
                } else {
                    Toast.makeText(getContext(), "Please enter text!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setUpBot();

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;

    }


    private void setUpBot() {
        try {
            InputStream stream = this.getResources().openRawResource(R.raw.credentials);
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream)
                    .createScoped(Lists.newArrayList(Collections.singleton("https://www.googleapis.com/auth/cloud-platform")));
            String projectId = ((ServiceAccountCredentials) credentials).getProjectId();

            SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
            SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(
                    FixedCredentialsProvider.create(credentials)).build();
            sessionsClient = SessionsClient.create(sessionsSettings);
            sessionName = SessionName.of(projectId, uuid);

            Log.d(TAG, "projectId : " + projectId);
        } catch (Exception e) {
            Log.d(TAG, "setUpBot: " + e.getMessage());
        }
    }

    private void sendMessageToBot(String message) {
        QueryInput input = QueryInput.newBuilder()
                .setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build();
        new SendMessageInBg(this ,sessionName, sessionsClient, input).execute();
    }

    @Override
    public void callback(DetectIntentResponse returnResponse) {
        if(returnResponse!=null) {
            String botReply = returnResponse.getQueryResult().getFulfillmentText();
            if(!botReply.isEmpty()){
                messageList.add(new com.example.cureya_chatbot.Message(botReply, true));
                ballView.setVisibility(View.GONE);
                chatAdapter.notifyDataSetChanged();
                speak(""+botReply.toString());
                Objects.requireNonNull(chatView.getLayoutManager()).scrollToPosition(messageList.size() - 1);
            }else {
                Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "failed to connect!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if (resultCode == -1 && null != data) {
                ArrayList<String> res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String inSpeech = res.get(0);
                recognition(inSpeech);
            }
        }
    }
    private void recognition(String text){


        if (!text.isEmpty()) {
            ;
            messageList.add(new com.example.cureya_chatbot.Message(text,false));
            editMessage.setText("");
            sendMessageToBot(text);
            ballView.setVisibility(View.VISIBLE);
            Objects.requireNonNull(chatView.getAdapter()).notifyDataSetChanged();
            Objects.requireNonNull(chatView.getLayoutManager())
                    .scrollToPosition(messageList.size() - 1);
        } else {
            Toast.makeText(getContext(), "Please enter text!", Toast.LENGTH_SHORT).show();
        }

        setUpBot();

    }

    private void listen(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");

        try {
            startActivityForResult(i, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getContext(), "Your device doesn't support Speech Recognition", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();

        }
        super.onDestroy();
    }
    private void speak(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


}