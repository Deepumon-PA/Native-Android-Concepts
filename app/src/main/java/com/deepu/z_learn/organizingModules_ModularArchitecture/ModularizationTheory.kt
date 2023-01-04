package com.deepu.z_learn.organizingModules_ModularArchitecture


//App Modularization

/*
Basically divide the project into three modules

1.Feature Modules:
should contain an individual and independent feature, can depend on library modules but should not depend on another feature or app layer modules

2.Library Modules
Contains code that can be reused by multiple feature modules, for eg: share ui components or logins, it can depend on another library but not feature or other app layer modules

3.App Modules
connects everything and can depend on both library and feature

 */