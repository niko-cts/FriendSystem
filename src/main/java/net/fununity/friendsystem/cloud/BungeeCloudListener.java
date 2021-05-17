package net.fununity.friendsystem.cloud;

import net.fununity.cloud.client.CloudClient;
import net.fununity.cloud.common.events.cloud.CloudEvent;
import net.fununity.cloud.common.events.cloud.CloudEventListener;
import net.fununity.friendsystem.FriendSystemBungee;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class BungeeCloudListener implements CloudEventListener {

    @Override
    public void newCloudEvent(CloudEvent cloudEvent) {
        switch (cloudEvent.getId()) {
            case CloudEvent.FRIENDS_REQUEST_SEND:
            case CloudEvent.FRIENDS_ADDED:
            case CloudEvent.FRIENDS_REMOVE:
                UUID friend = (UUID) cloudEvent.getData().get(1);
                ProxiedPlayer friendPlayer = FriendSystemBungee.getInstance().getProxy().getPlayer(friend);
                if (friendPlayer != null)  {
                    UUID player = (UUID) cloudEvent.getData().get(0);
                    String name = friendPlayer.getServer().getInfo().getName();
                    CloudClient.getInstance().sendEvent(new CloudEvent(CloudEvent.FORWARD_TO_SERVER).addData(name)
                            .addData(new CloudEvent(cloudEvent.getId()).addData(player).addData(friend)));
                }
                break;
            default:
                break;
        }
    }
}
