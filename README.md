# economyPlugins

About:

Plugins are for the youth group minecraft server.

WARNING: I know next to nothing about Java. All code is from watching tutorials and stackoverflow.



Initializing Commands: 

Create new Java file in the Commands directory

Include this first:
``` 
public class <File Name> implements CommandExecutor{
    private final Main main;

    public BalCommand(Main main) {
        this.main = main;
}

``` 
``` 
 @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    
    Code for Command
    }
``` 
Note:
Minecraft commands must end with return true or false.

Return true wont send anything to the user.
Return false will send the user multiple errors.


In Main.java

In the onEnable public void

Type this
 <This Is Your Input>
``` 
getServer().getPluginCommand("< Command Name For plugin.yml >").setExecutor(new < Name of Command File >(this));
``` 
In plugin.yml

under commands:

add Name you used for Main.java
and list description, usuage, and more.

Example:
``` 
Test:
  description: This is my test command!
  usage: /<command>
  aliases: Testing
  ``` 
