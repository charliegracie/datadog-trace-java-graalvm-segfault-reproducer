package org.kij.quarkus.datadog.trace.bug.config;

import org.slf4j.MDC;
import datadog.trace.api.CorrelationIdentifier;
import datadog.trace.context.ScopeListener;

public class Slf4jMdcInjectingScopeListener implements ScopeListener
{

    @Override
    public void afterScopeActivated() {
        MDC.put(CorrelationIdentifier.getTraceIdKey(), CorrelationIdentifier.getTraceId());
        MDC.put(CorrelationIdentifier.getSpanIdKey(), CorrelationIdentifier.getSpanId());
    }

    @Override
    public void afterScopeClosed() {
        MDC.remove(CorrelationIdentifier.getTraceIdKey());
        MDC.remove(CorrelationIdentifier.getSpanIdKey());
    }
}
