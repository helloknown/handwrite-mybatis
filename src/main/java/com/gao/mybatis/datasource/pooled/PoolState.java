package com.gao.mybatis.datasource.pooled;

import java.util.ArrayList;
import java.util.List;

public class PoolState {

    private PooledDataSource dataSource;

    // 空闲连接
    protected final List<PooledConnection> idleConnections = new ArrayList<PooledConnection>();
    // 活跃连接
    protected final List<PooledConnection> activeConnections = new ArrayList<>();

    // 请求次数
    protected long requestCount = 0;
    // 总请求时间
    protected long accumulateRequestTime = 0;
    // 总检查时间
    protected long accumulatedCheckoutTime = 0;
    // 超时连接次数
    protected long claimedOverdueConnectionCount = 0;
    // 总的超时连接数
    protected long accumulatedCheckoutTimeOfOverdueConnections = 0;

    // 总等待时间
    protected long accumulatedWaitTime = 0;
    // 要等待的时间
    protected long hadToWaitCount = 0;
    // 失败连接次数
    protected long badConnectionCount = 0;

    public PoolState(PooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public long getAverageRequestTime() {
        return requestCount == 0 ? 0 : accumulateRequestTime / requestCount;
    }

    public synchronized long getAverageWaitTime() {
        return hadToWaitCount == 0 ? 0 : accumulatedWaitTime / hadToWaitCount;
    }

    public long getClaimedOverdueConnectionCount() {
        return claimedOverdueConnectionCount;
    }

    public long getAverageOverdueCheckoutTime() {
        return claimedOverdueConnectionCount == 0 ? 0 :
                accumulatedCheckoutTimeOfOverdueConnections / claimedOverdueConnectionCount;
    }

    public long getAverageCheckoutTime() {
        return requestCount == 0 ? 0 : accumulatedCheckoutTime / requestCount;
    }

    public long getHadToWaitCount() {
        return hadToWaitCount;
    }

    public long getBadConnectionCount() {
        return badConnectionCount;
    }

    public int getIdleConnectionCount() {
        return idleConnections.size();
    }

    public synchronized int getActiveConnectionCount() {
        return activeConnections.size();
    }
}
