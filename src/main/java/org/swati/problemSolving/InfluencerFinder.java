package org.swati.problemSolving;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class InfluencerFinder {
    /**
     * Given a matrix of following between N LinkedIn users (with ids from 0 to N-1):
     * followingMatrix[i][j] == true iff user i is following user j
     * thus followingMatrix[i][j] doesn't imply followingMatrix[j][i].
     * Let's also agree that followingMatrix[i][i] == false
     *
     * Influencer is a user who is:
     * - followed by everyone else and
     * - not following anyone himself
     *
     * This method should find an Influencer by a given matrix of following,
     * or return -1 if there is no Influencer in this group.
     */
    public static int getInfluencer(boolean[][] followingMatrix) {
        for (int i = 0; i < followingMatrix.length; i++) {
            boolean isFollowed = true;
            boolean isFollowing = false;
            for (int j = 0; j < followingMatrix.length; j++) {
                //we are at a person who is following someone else
                if (followingMatrix[i][j]) {
                    isFollowing = true;
                    break;
                }
            }
            //this person was found to not follow anyone
            if (!isFollowing) {
                //check if everyone is following them
                for (int row = 0; row < followingMatrix.length; row++) {
                    //someone is not following them
                    if (row != i && !followingMatrix[row][i]) {
                        isFollowed = false;
                        break;
                    }
                }
            }
            if (isFollowed && !isFollowing) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        boolean[][] followingMatrix = new boolean[4][4];
        followingMatrix[0][0] = false;
        followingMatrix[0][1] = true;
        followingMatrix[0][2] = false;
        followingMatrix[0][3] = true;

        followingMatrix[1][0] = false;
        followingMatrix[1][1] = false;
        followingMatrix[1][2] = false;
        followingMatrix[1][3] = true;

        followingMatrix[2][0] = true;
        followingMatrix[2][1] = true;
        followingMatrix[2][2] = false;
        followingMatrix[2][3] = false;

        followingMatrix[3][0] = false;
        followingMatrix[3][1] = true;
        followingMatrix[3][2] = true;
        followingMatrix[3][3] = false;

        int influencer = getInfluencer(followingMatrix);
        System.out.println("Influencer " + influencer);
    }
}
