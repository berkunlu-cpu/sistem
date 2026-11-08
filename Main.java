import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {
		public static void main(String[] args) throws IOException {
                int port = System.getenv("PORT") != null
                ? Integer.parseInt(System.getenv("PORT"))
                : 8080;
				HttpServer server;
				try {
						server = HttpServer.create(new InetSocketAddress(port), 0);
				} catch (BindException e) {
						port = 8081;
						server = HttpServer.create(new InetSocketAddress(port), 0);
				}
				server.createContext("/", exchange -> {
						byte[] page = WEBSITE.getBytes(StandardCharsets.UTF_8);
						exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
						exchange.getResponseHeaders().set("X-Content-Type-Options", "nosniff");
						exchange.sendResponseHeaders(200, page.length);
						try (var output = exchange.getResponseBody()) {
								output.write(page);
						}
				});
				server.start();
				System.out.println("Tau-5 is running at http://localhost:" + port);
		}

		private static final String WEBSITE = """
						<!doctype html>
						<html lang="en">
						<head>
							<meta charset="utf-8">
							<meta name="viewport" content="width=device-width, initial-scale=1">
							<meta name="theme-color" content="#101510">
							<title>Tau-5 — Who Are We</title>
							<style>
								:root{--bg:#101510;--panel:#171e18;--line:#303b31;--text:#e9eee7;--muted:#a4afa3;--green:#b7d889;--amber:#e5b86b}
								*{box-sizing:border-box}html{scroll-behavior:smooth}body{margin:0;background:var(--bg);color:var(--text);font:16px/1.65 system-ui,-apple-system,Segoe UI,sans-serif}
								a{color:inherit;text-decoration:none}.wrap{width:min(1120px,calc(100% - 40px));margin:auto}.topbar{border-bottom:1px solid var(--line);background:#101510ee;position:sticky;top:0;z-index:2;backdrop-filter:blur(12px)}
								nav{height:68px;display:flex;align-items:center;justify-content:space-between}.brand{font-weight:800;letter-spacing:.11em;font-size:.88rem}.brand span{color:var(--green)}.links{display:flex;gap:26px;color:var(--muted);font-size:.9rem}.links a:hover{color:var(--green)}
								.hero{padding:94px 0 80px;background:radial-gradient(ellipse at 75% 20%,#34402b66,transparent 42%),linear-gradient(135deg,#101510,#151c16)}.eyebrow{color:var(--green);font-size:.76rem;letter-spacing:.18em;text-transform:uppercase;font-weight:700}.hero h1{font-size:clamp(2.7rem,7vw,5.6rem);line-height:1.04;letter-spacing:-.055em;max-width:780px;margin:18px 0}.hero p{color:var(--muted);font-size:1.1rem;max-width:620px}.actions{display:flex;gap:12px;margin-top:30px;flex-wrap:wrap}.button{display:inline-block;padding:11px 18px;border:1px solid var(--line);border-radius:4px;font-weight:650}.button.primary{background:var(--green);color:#182014;border-color:var(--green)}.button:hover{transform:translateY(-1px)}
								section{padding:72px 0;border-top:1px solid var(--line)}.section-head{display:flex;align-items:end;justify-content:space-between;gap:20px;margin-bottom:26px}.section-head h2{font-size:2rem;margin:5px 0 0;letter-spacing:-.03em}.section-head p{color:var(--muted);margin:0;max-width:430px}.grid{display:grid;grid-template-columns:repeat(2,minmax(0,1fr));gap:16px}.card{background:var(--panel);border:1px solid var(--line);padding:22px;border-radius:6px}.tag{font-size:.7rem;letter-spacing:.13em;color:var(--amber);text-transform:uppercase;font-weight:700}.card h3{margin:12px 0 6px;font-size:1.2rem}.card p{color:var(--muted);margin:0}.meta{display:block;color:#788477;font-size:.8rem;margin-top:18px}.team{display:grid;grid-template-columns:repeat(4,1fr);gap:12px}.person{padding:18px;border-left:2px solid var(--green);background:#171e18}.person strong{display:block}.person span{color:var(--muted);font-size:.88rem}footer{border-top:1px solid var(--line);padding:28px 0;color:var(--muted);font-size:.86rem}.footer-inner{display:flex;justify-content:space-between;gap:14px;flex-wrap:wrap}
								@media(max-width:760px){.links{gap:12px;font-size:.8rem}.grid{grid-template-columns:1fr}.team{grid-template-columns:repeat(2,1fr)}.hero{padding:70px 0}.section-head{display:block}.section-head p{margin-top:12px}}@media(max-width:430px){.links a:nth-child(2){display:none}.team{grid-template-columns:1fr}.wrap{width:min(100% - 28px,1120px)}}
							</style>
						</head>
						<body>
							<header class="topbar"><nav class="wrap"><a class="brand" href="#home">TAU<span> - </span>5</a><div class="links"><a href="#who-we-are">Who we are</a><a href="#team">Team Members & Ranks</a></div></nav></header>
							<main>
								<section class="hero" id="home"><div class="wrap"><div class="eyebrow">The Tau-5 collective</div><h1>The Broken Gods<br>The Team Who Protects</h1><p>We are the ones who will never fail in a task.</p><div class="actions"><a class="button primary" href="#who-are-we">Who are wee</a><a class="button" href="#team">The Team</a></div></div></section>
								<section id="who-we-are"><div class="wrap"><div class="section-head"><div><div class="eyebrow">Our purpose and principles</div><h2>Who are we &amp; what we believe</h2></div><p>Tau-5 is a Team of Robots who were made by Gods.</p></div><div class="grid">
									<article class="card"><span class="tag">Who are we</span><h3>Mobile Task Force Tau-5</h3><p>Mobile Task Force Tau-5, codename “Samsara”, is an elite Foundation task force made up of operatives who return from death. Answering to Foundation command, Tau-5 is deployed where no ordinary team could survive.</p></article>
									<article class="card"><span class="tag">What we believe</span><h3>Death is not the end of duty</h3><p>Tau-5 believes that death is not the end of duty. Its operatives accept dying as part of the mission, so that others do not have to. From their perspective, the Foundation’s work is a cycle that must continue, and every return from the dark is one more chance to protect humanity. Death, which once defined the limits of human life, is for them only a door they pass through again and again.</p></article>
								</div></div></section>
								<section id="team"><div class="wrap"><div class="section-head"><div><div class="eyebrow">The ones who serve The Foundation</div><h2>The collective</h2></div><p>A team of dedicated individuals working together to protect the Foundation.</p></div><div class="team">
									<div class="person"><strong>「Seeker」 </strong><span>Samsara Prime |FL|</span></div><div class="person"><strong>N/A</strong><span>[KARMA KEEPER]</span></div><div class="person"><strong>N/A</strong><span>[Cycle Warden]</span></div><div class="person"><strong>N/A</strong><span>[WAYFARER]</span></div><div class="person"><strong>N/A</strong><span>[RETURNER]</span></div><div class="person"><strong>N/A</strong><span>[INITIATE]</span></div>
								</div></div></section>
							</main>
							<footer><div class="wrap footer-inner"><span>TAU-5 · The Broken Gods</span><span>Collaborate thoughtfully. Keep the world Safe.</span></div></footer>
						</body>
						</html>
						""";
}
