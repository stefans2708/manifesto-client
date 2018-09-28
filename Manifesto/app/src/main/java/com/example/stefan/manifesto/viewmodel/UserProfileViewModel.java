package com.example.stefan.manifesto.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import com.example.stefan.manifesto.model.Post;
import com.example.stefan.manifesto.model.User;
import com.example.stefan.manifesto.repository.PostRepository;
import com.example.stefan.manifesto.repository.UserRepository;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class UserProfileViewModel extends BaseViewModel {

    private UserRepository userRepository = new UserRepository();
    private PostRepository postRepository = new PostRepository();

    private ObservableField<User> user = new ObservableField<>();
    private MutableLiveData<List<Post>> posts = new MutableLiveData<>();
    private ObservableField<Boolean> isMyProfile = new ObservableField<>();

    public UserProfileViewModel(int userId) {
        loadProfileData(userId);
        loadPostsOfUser(userId);
    }

    private void loadProfileData(int userId) {
        userRepository.getUser(userId, new SingleObserver<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(User u) {
                user.set(u);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private void loadPostsOfUser(int userId) {
        postRepository.getPostsOfUser(userId, new SingleObserver<List<Post>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Post> list) {
                posts.setValue(list);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    public void onBtnMessageClick() {

    }





    //============= GETTERS AND SETTERS ==============

    public ObservableField<User> getUser() {
        return user;
    }

    public void setUser(ObservableField<User> user) {
        this.user = user;
    }

    public ObservableField<Boolean> getIsMyProfile() {
        return isMyProfile;
    }

    public void setIsMyProfile(ObservableField<Boolean> isMyProfile) {
        this.isMyProfile = isMyProfile;
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    public void setPosts(MutableLiveData<List<Post>> posts) {
        this.posts = posts;
    }
}