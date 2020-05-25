![Global Logic Logo](https://media-exp1.licdn.com/dms/image/C4D0BAQG69AibJUpZuA/company-logo_200_200/0?e=1598486400&v=beta&t=O_Mz8twGahuU2o2iXTNlNBM_E-tStQTn8HEEKvy3N_c)

# Mobile Technical Interview

## Goal

Create an app that shows a simple list of items based on the following endpoint:
http://private-f0eea-mobilegllatam.apiary-mock.com/list
The app should show the following data :

* Title (at its full length, so take this into account when sizing your cells)
* Part of description
* A thumbnail for those who have a picture.
* Display a detail (with an image and full description) in a new screen when user
taps on an item

## Result

|   ![screne_1](https://raw.githubusercontent.com/moizest89/mobile-gl-latam/develop/screens/splash.png) |   ![screen_2](https://raw.githubusercontent.com/moizest89/mobile-gl-latam/develop/screens/main_list.png)  |
|:---|:---|
|    |    |


##Libreries

```
MIN_SDK_VERSION = 23
TARGET_SDK_VERSION = 29
COMPILE_SDK_VERSION = 29
BUILD_TOOLS_VERSION = '28.0.3'
SUPPORT_LIBRARY_VERSION = '28.0.0'
CONSTRAINT = '1.0.2'
APP_COMPAT = '1.1.0'
RECYCLER_VIEW = '1.1.0'
MATERIAL = '1.1.0'
CARDVIEW = '1.0.0'
LEGACY_SUPPORT = '1.0.0'
CONSTRAINT_LAYOUT = '1.1.3'
PALETTE = '1.0.0'
LIFECYCLE_EXTENSIONS = '2.2.0'
NAVIGATION = [
        FRAGMENT_KTX : "2.2.2",
        UI_KTX : "2.2.2",
        FRAGMENT : "2.2.2",
        UI : "2.2.2"
]
RETROFIT_VERSION = '2.3.0'
OKHTTP = '3.10.0'
GSON = '2.8.6'
GLIDE = '4.8.0'
kotlin_version = '1.3.71'
CORE_KTX = '1.2.0'
CIRCLE_IMAGEVIEW = '3.1.0'
```

## In summary:

*  The application was developed with Kotlin like main language
*  Basic MVVM implementation for show information
*  Support Portrait and Landscape orientation
*  SwipeForRefreshLayout for relad data
*  Splash Activity
*  Material Design
*  Dimens and String form resources




