package ru.kafkasample.producer.partitioner;

import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

public class NorthPartitioner implements Partitioner {

    public void configure(Map<String, ?> configs) {
    }

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
            Cluster cluster) {

        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();

        if ((keyBytes == null) || (!(key instanceof String))) {  // Only expect String keys, so we throw an exception if that is not the case.
            throw new IllegalArgumentException("We expect all messages to have customer name as key");
        }

        if (numPartitions>1) {
            if (((String) key).equals("north")) {  // north will always go to last partition
                return numPartitions - 1;
            }
            // Other records will get hashed to the rest of the partitions
            return (Math.abs(Utils.murmur2(keyBytes)) % (numPartitions - 1));
        } else
            return partitions.get(0).partition();
    }

    public void close() {
    }
}
