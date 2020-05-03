
public class Sweeping {

    AvlTree<Segment> tree = new AvlTree();
    PQueue pQueue = new PQueue();

    public void populatePQueue(Segment [] segments){
        for(Segment segment : segments){
            pQueue.insert(segment.p1);
            pQueue.insert(segment.p2);
        }
    }

    public boolean intersection(Segment[] segments){
        for(int i = 0; i < pQueue.size-1; i++){
            for(Segment segment : segments){
                if(pQueue.extractMin() == segments[i].startPoint()){
                    tree.insert(segments[i]);
                }else{
                    if(Point.segmentsIntersect(segments[i].p1, segments[i].p2, segment.p1, segment.p2)){
                        return true;
                    }
                }
            }

        }
        return false;
    }




}
