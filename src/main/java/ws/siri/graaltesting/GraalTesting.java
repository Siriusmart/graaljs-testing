package ws.siri.graaltesting;

import java.io.IOException;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import com.oracle.truffle.js.runtime.objects.Undefined;

import net.fabricmc.api.ModInitializer;

public class GraalTesting implements ModInitializer {
	public static final String MOD_ID = "graaltesting";

	@Override
	public void onInitialize() {
		try {
			doStuff();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void doStuff() throws IOException {
		Context ctx = Context.create("js");

		ctx.getBindings("js").putMember("module", Undefined.instance);
		ctx.eval("js", "let x = 5");
		ctx.eval(Source.newBuilder("js", "let y = x + 5", "test.js").build());
		System.out.println(ctx.getBindings("js").getMember("y"));
	}

	public static void main(String[] args) throws IOException {
		doStuff();
	}
}