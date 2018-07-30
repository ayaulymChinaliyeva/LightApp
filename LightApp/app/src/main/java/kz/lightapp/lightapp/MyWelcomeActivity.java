package kz.lightapp.lightapp;

import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.WelcomeConfiguration;

import com.stephentuso.welcome.WelcomeActivity;

public class MyWelcomeActivity extends WelcomeActivity {
    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.welcome_background)
                .page(new BasicPage(R.drawable.ic_search_black_24dp,
                        getString(R.string.welcomePageTitle1),
                        getString(R.string.welcomePageText1))
                )
                .page(new BasicPage(R.drawable.ic_playlist_add_check_black_24dp,
                        getString(R.string.welcomePageTitle2),
                        getString(R.string.welcomePageText2))
                )
                .page(new BasicPage(R.drawable.ic_person_black_24dp,
                        getString(R.string.welcomePageTitle3),
                        getString(R.string.welcomePageText3))
                )
                .swipeToDismiss(true)
                .build();
    }
}
