# ChanKugouLayout
  仿酷狗音乐滑动退出效果
# Demo
 ![image](https://github.com/ChanJLee/ChanKugouLayout/raw/master/src/demo.gif)

# How To Use
```xml
   <com.chan.slidingofflayout.KuGouLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/demo">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/m_testButton"
        android:text="测试"/>

</com.chan.slidingofflayout.KuGouLayout>
```
```java
 KuGouLayout layout = (KuGouLayout) button.getParent();
        layout.setOnCloseListener(new KuGouLayout.OnCloseListener() {
            @Override
            public void onCloseListener() {
                KuGouActivity.this.finish();
            }
        });
```



