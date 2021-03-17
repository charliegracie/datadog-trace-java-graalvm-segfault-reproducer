package org.kij.quarkus.datadog.trace.bug.config;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.TargetClass;

public class GraalJCToolsSubstitutions {
}

@TargetClass(className = "org.jctools.util.UnsafeRefArrayAccess")
final class Target_org_jctools_util_UnsafeRefArrayAccess {
    @Alias
    @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayIndexShift, declClass = Object[].class)
    public static int REF_ELEMENT_SHIFT;
}

@TargetClass(className = "org.jctools.util.UnsafeLongArrayAccess")
final class Target_org_jctools_util_UnsafeLongArrayAccess {
    @Alias
    @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayIndexShift, declClass = long[].class)
    public static int LONG_ELEMENT_SHIFT;
}

@TargetClass(className = "org.jctools.counters.FixedSizeStripedLongCounterFields")
final class Target_org_jctools_counters_FixedSizeStripedLongCounterFields {
    @Alias
    @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayBaseOffset, declClass = long[].class)
    public static long COUNTER_ARRAY_BASE;
}

// @TargetClass(className = "org.jctools.counters.FixedSizeStripedLongCounterFields")
// final class Target_org_jctools_counters_FixedSizeStripedLongCounterFields {
//  @Alias
//  @RecomputeFieldValue(kind = Kind.Custom, declClass = CounterArrayBase.class)
//  public static long COUNTER_ARRAY_BASE;
// }
//
// final class CounterArrayBase {
//  public static long COUNTER_ARRAY_BASE =
//      Math.max(UNSAFE.arrayBaseOffset(long[].class), PortableJvmInfo.CACHE_LINE_SIZE - 8);
// }

// final class RecomputeCounterArrayBase implements CustomFieldValueComputer {
//  @Override
//  public Object compute(
//      final MetaAccessProvider metaAccess,
//      final ResolvedJavaField original,
//      final ResolvedJavaField annotated,
//      final Object receiver) {
//    return Math.max(UNSAFE.arrayBaseOffset(long[].class), PortableJvmInfo.CACHE_LINE_SIZE - 8);
//  }
// }
//
// final class RecomputeCounterArrayBase2 implements CustomFieldValueTransformer {
//
//  @Override
//  public Object transform(MetaAccessProvider metaAccess, ResolvedJavaField original,
// ResolvedJavaField annotated, Object receiver, Object originalValue) {
//    return null;
//  }
// }
