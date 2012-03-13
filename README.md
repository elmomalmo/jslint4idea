# JSLINT 4 IntelliJ IDEA

*Note: This no longer builds successfully with Maven. You will need to create
an IDEA Plugin module for it in IDEA. Just for the moment...*

IDEA Community Edition is great. However the JavScript support is nothing more
than syntax highlighting. This plugin does JSLint inspection according to a
specified configuration.

## Building

Build using Maven, then pop the JAR in IDEA's plugins folder.

Works for versions up to 10.5.2

## Notes

Currently, this plugin uses a deprecated version of the plugin API and so is
not compatible with version 11.