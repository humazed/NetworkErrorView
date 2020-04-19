# NetworkErrorView
A custom view for displaying a network error view with beautiful and simple animation.

![Alt text](screenShots/1.gif?raw=true)

### Usage

```xml
<humazed.github.com.NetworkErrorView
    android:id="@+id/networkErrorView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
```


```java
networkErrorView.show();
networkErrorView.hide();
```

**don't use** `networkErrorView.setVisibility(View.VISIBLE);`


**available customization:**
```xml
app:nev_message="new error message"
app:nev_messageColor="#FFD50000"
app:nev_imageTint="#FFD50000"
app:nev_retryText="Try again"
app:nev_retryBackground="#FFD50000"
app:nev_retryColor="#FFD50000"
```
---

### Dependency

Add this to your module's `build.gradle` file (make sure the version matches the last [release](https://github.com/humazed/RoomAssety/releases/latest)):

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add the dependency
```gradle
dependencies {
    // ... other dependencies
    implementation 'com.github.humazed:NetworkErrorView:1.0.2'
}
```
-----


### Credits

Extracted and repackaged from [Nick Butcher's](https://github.com/nickbutcher) [Plaid](https://github.com/nickbutcher/plaid) App.
