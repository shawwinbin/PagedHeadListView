PagedHeadListView Android Library
================================


![Demo Screenshot 1](https://raw.githubusercontent.com/JorgeCastilloPrz/PagedHeadListView/master/app/src/main/res/raw/preview1.gif)
![Demo Screenshot 2](https://raw.githubusercontent.com/JorgeCastilloPrz/PagedHeadListView/master/app/src/main/res/raw/preview2.gif)
![Demo Screenshot 3](https://raw.githubusercontent.com/JorgeCastilloPrz/PagedHeadListView/master/app/src/main/res/raw/preview3.gif)


Check PagedHeadListView Demo application on GooglePlay:<br />
<a target="_blank" href="https://play.google.com/store/apps/details?id=com.jorgecastilloprz.pagedheadlistview.testapp">
  <img alt="Get it on Google Play" src="https://raw.githubusercontent.com/JorgeCastilloPrz/ExpandablePanel/master/app/src/main/res/raw/en_generic_rgb_wo_60.png" />
</a>

Details
-------

If you are looking for a listview with a paged header this is the dependency you are looking for. With  ```PagedHeadListView``` you will be able to set your own fragments as new pages for the header, and a brand new indicator will be added automatically. 
You can play with all the custom attributes provided with the library to set indicator colors and position, ViewPager drag animation, and touch behaviour for the header view.
```PagedHeadListView``` supports ```API 11 - Android SDK 3.0 (HoneyComb)``` as minimum.

This lib brings a custom ListView class called ```PagedHeadListView``` to the final user. Use it to integrate the component in your own Android application.
You will not need to create your own code with ```FragmentStateAdapter``` or ```ViewPager``` by yourself for creating this cool user experience, as the library does the boring work for you.

Custom Attributes
-------------

This android library allows you to customize the following properties. Feel free to combine them to create your cool user interfaces:

* ```pagedheadlistview:headerHeight```: The height you want to give to the header (dimen resources allowed).
* ```pagedheadlistview:disableVerticalTouchOnHeader```: Set it to true if you want the header to not be affected by verticall ListView scrolling.
* ```pagedheadlistview:indicatorBgColor```: Color resource for indicator background.
* ```pagedheadlistview:indicatorColor```: Color resource for indicator view.
* ```pagedheadlistview:indicatorType```: Type for the indicator. Types allowed are: ```topAligned```, ```bottomAligned```, and ```none```. If you set the value to none, no indicator will be included.
* ```pagedheadlistview:pageTransformer```: You can set it to ```depth```, ```zoomout```, ```rotate```, ```scale```, ```flip```, or ```accordion``` for using one of the stated ViewPager drag animations. You can cutomize your own pageTransformer by extending ```ViewPager.PageTransformer``` native Android class and by adding it to the ```PagedHeadListView``` using the following method:

```java
mPagedHeadListView.setHeaderPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer customPageTransformer);
```

Usage
-----

In order to make it work, you will need to use ```PagedHeadListView``` class into your Android code. Please, don't forget to add the aditional ```xmlns:pagedheadlistview="http://schemas.android.com/apk/res-auto"``` namespace to it. 

``` xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:pagedheadlistview="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.jorgecastilloprz.pagedheadlistview.PagedHeadListView
        android:id="@+id/pagedHeadListView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        pagedheadlistview:headerHeight="@dimen/header_height"
        pagedheadlistview:indicatorType="bottomAligned"
        pagedheadlistview:disableVerticalTouchOnHeader="true"
        pagedheadlistview:indicatorBgColor="@color/material_green"
        pagedheadlistview:indicatorColor="@color/material_light_green" />

</RelativeLayout>
 ```

In your java code, use ```PagedHeadListView``` like this:

```java
mPagedHeadList = (PagedHeadListView) rootView.findViewById(R.id.pagedHeadListView);

mPagedHeadList.addFragmentToHeader(new FirstHeaderFragment());
mPagedHeadList.addFragmentToHeader(new SecondHeaderFragment());
mPagedHeadList.addFragmentToHeader(new ThirdHeaderFragment());
mPagedHeadList.addFragmentToHeader(new FourthHeaderFragment());
mPagedHeadList.addFragmentToHeader(new FifthHeaderFragment());

mPagedHeadList.setHeaderOffScreenPageLimit(4);
mPagedHeadList.setHeaderPageTransformer(PageTransformerTypes.FLIP);

ArrayList<String> mockItemList = new ArrayList<String>();

for (int i = 0; i < 50; i++)
    mockItemList.add(getResources().getString(R.string.mock_item) + " " + (i+1));

MockListAdapter mockListAdapter = new MockListAdapter(getActivity(), R.layout.mock_list_item, mockItemList);
mPagedHeadList.setAdapter(mockListAdapter);
```

Following methods are avaiable to setup the pager programatically:
```java
//Types vaiable: DEPTH, ZOOMOUT, ROTATE, SCALE, FLIP, ACCORDION.
mPagedHeadListView.setHeaderPageTransformer(PageTransformerTypes pageTransformerType);

//This is a mapping for normal setPageTransformer from Android support ViewPager.
mPagedHeadListView.setHeaderPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer customPageTransformer);

//Dynamic mode to set header's view height. (Always in pixels)
mPagedHeadListView.setHeaderHeight(500);
mPagedHeadListView.setHeaderHeight(getResources().getDimensionPixelSize(R.dimen.header_height_test));

//For the user to be able to listen for header ViewPager events.
mPagedHeadListView.setOnHeaderPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener);

mPagedHeadListView.setHeaderOffScreenPageLimit(int offScreenPageLimit);

mPagedHeadListView.setIndicatorBgColor(int indicatorBgColor);
mPagedHeadListView.setIndicatorColor(int indicatorColor);

//If you want to disable vertical touch on header programatically.
mPagedHeadListView.disableVerticalTouchOnHeader();

mPagedHeadList.setIndicatorBgColor(getResources().getColor(R.color.material_green));
mPagedHeadList.setIndicatorColor(getResources().getColor(R.color.material_light_green));
```


For being able to listen to header page change events, use ```setOnHeaderPageChangeListener``` which needs a ViewPager.OnPageChangeListener item as an argument. 


Import PagedHeadListView dependency
---------------------------------

At this very moment i have not uploaded it yet. But it will come pretty soon.

Add the next code to your build.gradle project dependencies:
```groovy
dependencies {
    compile 'com.github.jorgecastilloprz:pagedheadlistview:1.0.0@aar'
}
```
Set the mavenCentral repo into the external build.gradle:
```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:0.12.+'
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}
```
If you are using Maven, use the following code:
```xml
<dependency>
  <groupId>com.github.jorgecastilloprz</groupId>
  <artifactId>pagedheadlistview</artifactId>
  <version>1.0.0</version>
  <type>aar</type>
</dependency>
```
TODO - AT WORK
--------------

* Give the users the possibility to extend AbstractPagedHeadIndicator class to develop their own indicators. A new setter will be added for it when i get sure that it is fully supported.
* Maven central deploy (comming in 1-2 days at most).

Developer
---------
* Jorge Castillo Pérez <jorge.castillo.prz@gmail.com>
* Twitter acc - @JorgeCastilloPr (https://twitter.com/jorgecastillopr)
* Professional site - http://jorgecastilloprz.github.io

License
-------

    Copyright 2014 Jorge Castillo Pérez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.