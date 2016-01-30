package org.swtk.data.gngrams.parse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import redis.clients.jedis.Jedis;

public final class JedisTest {

	// @Test
	// test case only works if redis-server is running ... 
	public void run() throws Throwable {
		Jedis jedis = new Jedis("localhost");
		jedis.connect();

		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		assertEquals("bar", value);

		assertTrue(jedis.keys("no").isEmpty());
		assertFalse(jedis.keys("*").isEmpty());

		assertTrue(jedis.keys("fo").isEmpty());
		assertFalse(jedis.keys("fo*").isEmpty());

		jedis.close();
	}
}
