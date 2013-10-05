#!/bin/sh
# CHROMIUM BASE LIB - contains org.chromium.base source code

# Make sure you setup and run ../../env.sh first

export PROJ=$ACV_HOME/base

# clean up
rm -rf $PROJ/src/org/chromium/base/*

# sources for org.chromium.base
cp -r $BUILD/base/android/java/src/* $PROJ/src/

# remove un-used .template files in src
rm $PROJ/src/org/chromium/base/*.template

# .template files are generated at chromium build time, so copy over .java files
cp -r $BUILD/out/Release/gen/templates/org/chromium/base/* $PROJ/src/org/chromium/base/

# need this jar file for some annotation support
cp $BUILD/out/Release/lib.java/jsr_305_javalib.jar $PROJ/libs/
