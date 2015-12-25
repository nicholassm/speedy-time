# Low Memory Java Date Time Library

The date and time classes of `java.time` use several instance fields to represent the year, month, etc. and that is heavy-weight memory wise. This library stores time as a single `long` and is a classic trade-off between memory and computation time. However, the compact representation can be more cache friendly depending on the application and tuning. It's also beneficial in applications that need to keep a large number of date time instances in memory. 
