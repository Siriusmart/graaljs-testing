package ws.siri.graaltesting;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.graalvm.polyglot.Context;

import net.fabricmc.api.ModInitializer;

public class GraalTesting implements ModInitializer {
	public static final String MOD_ID = "graaltesting";

	@Override
	public void onInitialize() {
		System.out.println("Blocking");
		doStuff();

		CompletableFuture.runAsync(() -> {
			try {
				System.out.println("Async");
				doStuff();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void bonk() {
		System.out.println("hi");
	}

	public static void doStuff() {
		Context ctx = Context.newBuilder("js").allowAllAccess(true).build();
		ctx.eval("js", "let String = Packages.java.lang.String;");
		ctx.eval("js", "let GraalTesting = Packages.ws.siri.graaltesting.GraalTesting;");
		ctx.eval("js", "GraalTesting.bonk();");
	}

	public static void main(String[] args) throws IOException {
		doStuff();

		CompletableFuture.runAsync(() -> {
			try {
				doStuff();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}