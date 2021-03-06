package com.orange.commons.cache;

import net.spy.memcached.*;
import net.spy.memcached.internal.BulkFuture;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.transcoders.SerializingTranscoder;
import net.spy.memcached.transcoders.Transcoder;

import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 用于本地开发的时候，避免搭建memcached缓存
 * 
 * @author wuyajun
 * 
 */
public class MockMemcachedClient implements MemcachedClientIF {
	private final Transcoder<Object>          transcoder;
	private final HashMap<String, CachedData> cache;

	public MockMemcachedClient() {
		this.transcoder = new SerializingTranscoder();
		this.cache = new HashMap<String, CachedData>();
	}

	@Override
	public Object get(String key) throws OperationTimeoutException {
		return this.get(key, this.transcoder);
	}

	@Override
	public <T> T get(String key, Transcoder<T> transcoder) throws OperationTimeoutException {
		if (this.cache.get(key) == null) {
			return null;
		}
		return transcoder.decode(this.cache.get(key));
	}

	@Override
	public Future<Boolean> set(String key, int exp, Object o) {
		return this.set(key, exp, o, this.transcoder);
	}

	@Override
	public <T> Future<Boolean> set(String key, int ttl, T value, Transcoder<T> transcoder) {
		this.cache.put(key, transcoder.encode(value));
		return new FuturePrimitive(true);
	}

	@Override
	public void shutdown() {
	}

	@Override
	public Collection<SocketAddress> getAvailableServers() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Collection<SocketAddress> getUnavailableServers() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Transcoder<Object> getTranscoder() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public NodeLocator getNodeLocator() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Boolean> append(long cas, String key, Object val) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<Boolean> append(long cas, String key, T val, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Boolean> prepend(long cas, String key, Object val) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<Boolean> prepend(long cas, String key, T val, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<CASResponse> asyncCAS(String key, long casId, T value, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<CASResponse> asyncCAS(String key, long casId, Object value) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> CASResponse cas(String key, long casId, T value, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public CASResponse cas(String key, long casId, Object value) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<Boolean> add(String key, int exp, T o, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Boolean> add(String key, int exp, Object o) {
		Object value = this.cache.get(key);
		if (value != null) {
			return new FuturePrimitive(false);
		}
		this.set(key, exp, o, this.transcoder);
		return new FuturePrimitive(true);
	}

	@Override
	public <T> Future<Boolean> replace(String key, int exp, T o, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Boolean> replace(String key, int exp, Object o) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<T> asyncGet(String key, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Object> asyncGet(String key) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<CASValue<Object>> asyncGetAndTouch(String key, int exp) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<CASValue<T>> asyncGetAndTouch(String key, int exp, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public CASValue<Object> getAndTouch(String key, int exp) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> CASValue<T> getAndTouch(String key, int exp, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<CASValue<T>> asyncGets(String key, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<CASValue<Object>> asyncGets(String key) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> CASValue<T> gets(String key, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public CASValue<Object> gets(String key) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> BulkFuture<Map<String, T>> asyncGetBulk(Collection<String> keys, Iterator<Transcoder<T>> tcs) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> BulkFuture<Map<String, T>> asyncGetBulk(Collection<String> keys, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public BulkFuture<Map<String, Object>> asyncGetBulk(Collection<String> keys) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> BulkFuture<Map<String, T>> asyncGetBulk(Transcoder<T> tc, String... keys) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public BulkFuture<Map<String, Object>> asyncGetBulk(String... keys) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Map<String, T> getBulk(Collection<String> keys, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Map<String, Object> getBulk(Collection<String> keys) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Map<String, T> getBulk(Transcoder<T> tc, String... keys) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Map<String, Object> getBulk(String... keys) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<Boolean> touch(String key, int exp, Transcoder<T> tc) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<Boolean> touch(String key, int exp) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Map<SocketAddress, String> getVersions() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Map<SocketAddress, Map<String, String>> getStats() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Map<SocketAddress, Map<String, String>> getStats(String prefix) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long incr(String key, int by) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long decr(String key, int by) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long incr(String key, int by, long def, int exp) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long decr(String key, int by, long def, int exp) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Long> asyncIncr(String s, long l, long l1, int i) {
		return null;
	}

	@Override
	public Future<Long> asyncIncr(String s, int i, long l, int i1) {
		return null;
	}

	@Override
	public Future<Long> asyncDecr(String s, long l, long l1, int i) {
		return null;
	}

	@Override
	public Future<Long> asyncDecr(String s, int i, long l, int i1) {
		return null;
	}

	@Override
	public Future<Long> asyncIncr(String key, int by) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Long> asyncDecr(String key, int by) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long incr(String key, int by, long def) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long decr(String key, int by, long def) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Long> asyncIncr(String s, long l, long l1) {
		return null;
	}

	@Override
	public Future<Long> asyncIncr(String s, int i, long l) {
		return null;
	}

	@Override
	public Future<Long> asyncDecr(String s, long l, long l1) {
		return null;
	}

	@Override
	public Future<Long> asyncDecr(String s, int i, long l) {
		return null;
	}

	@Override
	public Future<Boolean> delete(String key) {
		cache.remove(key);
		return new FuturePrimitive(true);
	}

	@Override
	public Future<Boolean> flush(int delay) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Boolean> flush() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public boolean shutdown(long timeout, TimeUnit unit) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public boolean waitForQueues(long timeout, TimeUnit unit) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public boolean addObserver(ConnectionObserver obs) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public boolean removeObserver(ConnectionObserver obs) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Set<String> listSaslMechanisms() {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Boolean> append(String arg0, Object arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<Boolean> append(String arg0, T arg1, Transcoder<T> arg2) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<CASResponse> asyncCAS(String arg0, long arg1, int arg2, Object arg3) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> OperationFuture<CASResponse> asyncCAS(String arg0, long arg1, int arg2, T arg3, Transcoder<T> arg4) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Long> asyncDecr(String arg0, long arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public BulkFuture<Map<String, Object>> asyncGetBulk(Iterator<String> arg0) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> BulkFuture<Map<String, T>> asyncGetBulk(Iterator<String> arg0, Iterator<Transcoder<T>> arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> BulkFuture<Map<String, T>> asyncGetBulk(Iterator<String> arg0, Transcoder<T> arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Long> asyncIncr(String arg0, long arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public CountDownLatch broadcastOp(BroadcastOpFactory arg0) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public CountDownLatch broadcastOp(BroadcastOpFactory arg0, Collection<MemcachedNode> arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public CASResponse cas(String arg0, long arg1, int arg2, Object arg3) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> CASResponse cas(String arg0, long arg1, int arg2, T arg3, Transcoder<T> arg4) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long decr(String arg0, long arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long decr(String arg0, long arg1, long arg2) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long decr(String arg0, long arg1, long arg2, int arg3) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Boolean> delete(String arg0, long arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Map<String, Object> getBulk(Iterator<String> arg0) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Map<String, T> getBulk(Iterator<String> arg0, Transcoder<T> arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long incr(String arg0, long arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long incr(String arg0, long arg1, long arg2) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public long incr(String arg0, long arg1, long arg2, int arg3) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public Future<Boolean> prepend(String arg0, Object arg1) {
		throw new RuntimeException("not yet implemented");
	}

	@Override
	public <T> Future<Boolean> prepend(String arg0, T arg1, Transcoder<T> arg2) {
		throw new RuntimeException("not yet implemented");
	}
}

class FuturePrimitive implements Future<Boolean> {

	private Boolean value;

	public FuturePrimitive() {
	}

	public FuturePrimitive(Boolean value) {
		this.value = value;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public Boolean get() throws InterruptedException, ExecutionException {
		return value == null ? Boolean.TRUE : value;
	}

	@Override
	public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException {
		return value == null ? Boolean.TRUE : value;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return true;
	}
}