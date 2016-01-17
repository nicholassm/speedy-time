# Small Memory Footprint Java Date Time Library

The date and time classes of `java.time` use several instance fields to represent the year, month, etc. and that is heavy-weight memory wise. This library stores time as a single `long` and is a classic trade-off between memory and computation time. However, the compact representation can be more cache friendly depending on the application and tuning. It's also beneficial in applications that need to keep a large number of date time instances in memory.

The primary use case for this library is in financial applications where memory and latency are of high importance. Typically that would be in the price flow where data rates are high.

**Note:** The implementation of the date related fields in this library is using the same computations as are used in the method `ofEpochDay` in `java.time.LocalDate`.

## Guide
There are two flavors of the library: Using it as utility methods (static methods) or as plan objects.

### Using Utility Methods
First do a static import:

    import static systems.speedy.lmf.time.UnixTimestampConverter.*;

Then use any of the utility methods, e.g. `yearOf` to extract the year part of the timestamp:

    int year = yearOf(unixTimeStampInMillis);

### Using Objects

First create a `UTCDateTime`:

    long unixTimeStampInMillis = // Get the timestamp, e.g. System.currentTimeMilis()
    UTCDateTime udt            = new UTCDateTime(unixTimeStampInMillis);

Then use one of the getter methods, e.g.:

    int year = udt.getYear();