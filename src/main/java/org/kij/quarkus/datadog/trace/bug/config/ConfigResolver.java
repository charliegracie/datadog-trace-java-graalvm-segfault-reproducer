package org.kij.quarkus.datadog.trace.bug.config;

import java.util.Properties;
import java.util.stream.Stream;
import org.eclipse.microprofile.config.ConfigProvider;
import datadog.trace.api.config.GeneralConfig;
import datadog.trace.api.config.JmxFetchConfig;
import datadog.trace.api.config.TraceInstrumentationConfig;
import datadog.trace.api.config.TracerConfig;

public class ConfigResolver
{
    static final String DD_PREFIX = "dd.";

    public static Properties resolve() {
        final Properties properties = new Properties();

        //@formatter:off
        Stream.of(TracingConf.values())
                .sequential()
                .forEach(conf -> 
                    ConfigProvider
                        .getConfig()
                        .getOptionalValue(conf.fullName, String.class)
                        .ifPresent(value -> properties.putIfAbsent(conf.propName, value)));
        //@formatter:on

        return properties;
    }

    static enum TracingConf {

        // General configuration
        ENV(GeneralConfig.ENV), //
        VERSION(GeneralConfig.VERSION), //
        TAGS(GeneralConfig.TAGS), //
        SERVICE_NAME(GeneralConfig.SERVICE_NAME), //
        HEALTH_METRICS_ENABLED(GeneralConfig.HEALTH_METRICS_ENABLED), //
        HEALTH_METRICS_STATSD_HOST(GeneralConfig.HEALTH_METRICS_STATSD_HOST), //
        HEALTH_METRICS_STATSD_PORT(GeneralConfig.HEALTH_METRICS_STATSD_PORT), //
        PERF_METRICS_ENABLED(GeneralConfig.PERF_METRICS_ENABLED), //

        // Tracing configuration
        ID_GENERATION_STRATEGY(TracerConfig.ID_GENERATION_STRATEGY), //
        WRITER_TYPE(TracerConfig.WRITER_TYPE), //
        PRIORITIZATION_TYPE(TracerConfig.PRIORITIZATION_TYPE), //
        TRACE_AGENT_URL(TracerConfig.TRACE_AGENT_URL), //
        AGENT_HOST(TracerConfig.AGENT_HOST), //
        TRACE_AGENT_PORT(TracerConfig.TRACE_AGENT_PORT), //
        AGENT_PORT_LEGACY(TracerConfig.AGENT_PORT_LEGACY), //
        AGENT_UNIX_DOMAIN_SOCKET(TracerConfig.AGENT_UNIX_DOMAIN_SOCKET), //
        AGENT_TIMEOUT(TracerConfig.AGENT_TIMEOUT), //
        PRIORITY_SAMPLING(TracerConfig.PRIORITY_SAMPLING), //
        PRIORITY_SAMPLING_FORCE(TracerConfig.PRIORITY_SAMPLING_FORCE), // *
        SERVICE_MAPPING(TracerConfig.SERVICE_MAPPING), //
        SPAN_TAGS(TracerConfig.SPAN_TAGS), //
        TRACE_ANALYTICS_ENABLED(TracerConfig.TRACE_ANALYTICS_ENABLED), //
        TRACE_SAMPLING_SERVICE_RULES(TracerConfig.TRACE_SAMPLING_SERVICE_RULES), //
        TRACE_SAMPLING_OPERATION_RULES(TracerConfig.TRACE_SAMPLING_OPERATION_RULES), //
        TRACE_SAMPLE_RATE(TracerConfig.TRACE_SAMPLE_RATE), //
        TRACE_RATE_LIMIT(TracerConfig.TRACE_RATE_LIMIT), //
        TRACE_REPORT_HOSTNAME(TracerConfig.TRACE_REPORT_HOSTNAME), //
        HEADER_TAGS(TracerConfig.HEADER_TAGS), //
        HTTP_SERVER_ERROR_STATUSES(TracerConfig.HTTP_SERVER_ERROR_STATUSES), //
        HTTP_CLIENT_ERROR_STATUSES(TracerConfig.HTTP_CLIENT_ERROR_STATUSES), //
        SPLIT_BY_TAGS(TracerConfig.SPLIT_BY_TAGS), //
        SCOPE_DEPTH_LIMIT(TracerConfig.SCOPE_DEPTH_LIMIT), //
        SCOPE_STRICT_MODE(TracerConfig.SCOPE_STRICT_MODE), //
        SCOPE_INHERIT_ASYNC_PROPAGATION(TracerConfig.SCOPE_INHERIT_ASYNC_PROPAGATION), //
        PARTIAL_FLUSH_MIN_SPANS(TracerConfig.PARTIAL_FLUSH_MIN_SPANS), //
        PROPAGATION_STYLE_EXTRACT(TracerConfig.PROPAGATION_STYLE_EXTRACT), //
        PROPAGATION_STYLE_INJECT(TracerConfig.PROPAGATION_STYLE_INJECT), //

        // The only one from instrumentation required to override default activation
        LOGS_INJECTION_ENABLED(TraceInstrumentationConfig.LOGS_INJECTION_ENABLED), //

        // JMX configuration
        JMX_TAGS(JmxFetchConfig.JMX_TAGS), //
        JMX_FETCH_ENABLED(JmxFetchConfig.JMX_FETCH_ENABLED), //
        JMX_FETCH_CONFIG_DIR(JmxFetchConfig.JMX_FETCH_CONFIG_DIR), //
        JMX_FETCH_CONFIG(JmxFetchConfig.JMX_FETCH_CONFIG), //
        JMX_FETCH_CHECK_PERIOD(JmxFetchConfig.JMX_FETCH_CHECK_PERIOD), //
        JMX_FETCH_REFRESH_BEANS_PERIOD(JmxFetchConfig.JMX_FETCH_REFRESH_BEANS_PERIOD), //
        JMX_FETCH_STATSD_HOST(JmxFetchConfig.JMX_FETCH_STATSD_HOST), //
        JMX_FETCH_STATSD_PORT(JmxFetchConfig.JMX_FETCH_STATSD_PORT);


        final String fullName;
        final String propName;

        private TracingConf(final String propName) {
            this.propName = propName;
            this.fullName = DD_PREFIX + propName;
        }

    }
}
