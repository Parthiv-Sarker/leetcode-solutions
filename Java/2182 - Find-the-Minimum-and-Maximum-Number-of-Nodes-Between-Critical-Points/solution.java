/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        int firstCritical = -1;
        int prevCritical = -1;

        int position = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {

            ListNode next = curr.next;

            boolean isCritical =
                (curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {

                if (firstCritical == -1) {
                    firstCritical = position;
                }

                if (prevCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        position - prevCritical
                    );
                }

                prevCritical = position;

                maxDistance = position - firstCritical;
            }

            prev = curr;
            curr = next;
            position++;
        }

        if (firstCritical == -1 || firstCritical == prevCritical) {
            return new int[] {-1, -1};
        }

        return new int[] {minDistance, maxDistance};
    }
}