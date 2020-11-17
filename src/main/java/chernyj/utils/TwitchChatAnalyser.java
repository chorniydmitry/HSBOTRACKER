package chernyj.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.TwirkBuilder;
import com.gikk.twirk.events.TwirkListener;

import observer.RegisterObserver;
import observer.RegisterSubject;
/**
 * @author Chernyj Dmitry
 *
 */
public class TwitchChatAnalyser implements RegisterSubject {
	
	private ArrayList<RegisterObserver> observers = new ArrayList<>();
	
	private static final String ONLOAD_MESSAGE = "ВНИМАНИЕ! Запускается аттракцион невиданной щедрости."
	+" Победители получат подписки на канал. Для участия в конкурсе напишите в чате !reg и числа, на которые хотите поставить" 
	+" например !reg 20 30 40. Если не хотите придумывать числа, просто укажите нули, например !reg 0 0 0"
	+" и тогда Бенброд придумает числа вместо вас(рандом - это весело!).";
	
	private static final String ONCLOSE_MESSAGE = "ВНИМАНИЕ! Регистрация завершена! Номера больше не принимаются!";
	
	private Twirk twirk;

	public TwitchChatAnalyser() {
		twirk = null;
		try {
			twirk = new TwirkBuilder(Constants.CHANNEL, Constants.MY_NICK, Constants.MY_PASS).setVerboseMode(true).build();
			twirk.addIrcListener(getOnDisconnectListener(twirk));
			twirk.addIrcListener(getOnMessageSentListener(twirk));
			twirk.connect();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return;
		}
		sendMessage(ONLOAD_MESSAGE);
	}
	
	public void close() {
		sendMessage(ONCLOSE_MESSAGE);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		twirk.disconnect();
		twirk.close();
	}
	
	public void sendMessage(String message) {
		twirk.channelMessage(message);
	}

	private TwirkListener getOnMessageSentListener(final Twirk twirk) {
		return new TwirkListener() {
			@Override
			public void onAnything(String unformatedMessage) {
				// TwirkListener.super.onAnything(unformatedMessage);
				if (unformatedMessage.contains("PRIVMSG")) {
					String message = unformatedMessage.split("PRIVMSG " + Constants.CHANNEL + " :")[1];
					String user = StringUtils.substringBetween(unformatedMessage, "display-name=", ";");

					if (message.contains("!reg")) {
						String numbersUnformatted = message.split("!reg")[1].trim();
						String[] numbersArr = numbersUnformatted.split(" ");
						ArrayList<Integer> numbersList = new ArrayList<Integer>();

						for (String num : numbersArr) {
							int number = -1;
							
							try {
								number = Integer.parseInt(num);
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}

							if (number != -1) {
								numbersList.add(number);
							} else if(num.equalsIgnoreCase("r")) {
								numbersList.add(0);
							}
						}
						notifyObserver(user, numbersList);
					}
				}
			}
		};
	}

	private static TwirkListener getOnDisconnectListener(final Twirk twirk) {

		return new TwirkListener() {
			@Override
			public void onDisconnect() {
				// Twitch might sometimes disconnects us from chat. If so, try to reconnect.
				try {
					if (!twirk.connect())
						// Reconnecting might fail, for some reason. If so, close the connection and
						// release resources.
						twirk.close();
				} catch (IOException e) {
					// If reconnection threw an IO exception, close the connection and release
					// resources.
					twirk.close();
				} catch (InterruptedException e) {
				}
			}

		};
	}

	@Override
	public void register(RegisterObserver o) {
		o.toString();
		observers.add(o);

	}

	@Override
	public void unregister(RegisterObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObserver(String userName, ArrayList<Integer> numbers) {
		for (RegisterObserver o : observers) {
			o.toString();
			o.update(userName, numbers);
		}
	}

}
