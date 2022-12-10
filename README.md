# Setup

Clone this repository and run Gradle `jar` task (e.g. `$ gradlew jar`).

# Startup

Run application with `$ java -jar path_to_jar connect ip port user_name`

## Arguments

* `ip`: Specifies group chat ip address. Must be IPv4 and be in Multicast group.
* `port`: Specifies port to make connection with.
* `user_name`: Specifies user's name that will be visible to other users.

# Usage

Type messages in the command line you connected with. They will appear to all connected users.

To disconnect, simply terminate executable.