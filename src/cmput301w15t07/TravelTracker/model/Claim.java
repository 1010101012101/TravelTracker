/*
 *   Copyright 2015 Kirby Banman,
 *                  Stuart Bildfell,
 *                  Elliot Colp,
 *                  Christian Ellinger,
 *                  Braedy Kuzma,
 *                  Ryan Thornhill
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cmput301w15t07.TravelTracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import cmput301w15t07.TravelTracker.serverinterface.Constants.Type;

/**
 * Model object for Claim made by Users acting as Claimants.
 * 
 * @author kdbanman,
 *         braedy,
 *         therabidsquirel
 *
 */
public class Claim extends Document {
    private UUID user;
    private UUID approver;
    private Status status;
    private Date startDate;
    private Date endDate;
    private ArrayList<Destination> destinations;
    private ArrayList<ApproverComment> comments;
    private ArrayList<UUID> tags;
    
    /**
     * Package protected constructor, intended for use only by DataSource.
     * 
     * @param docID UUID document identifier
     */
    Claim(UUID docID, UUID userID) {
        super(docID);
        setType(Type.CLAIM);
        
        user = userID;
        approver = null; // As a claim won't have an approver to begin with, there's no better default than null unfortunately.
        status = Status.IN_PROGRESS;
        startDate = new Date();
        endDate = new Date();
        destinations = new ArrayList<Destination>();
        comments = new ArrayList<ApproverComment>();
        tags = new ArrayList<UUID>();
    }
    
    /**
     * Private no-args constructor for GSON.
     */
    @SuppressWarnings("unused")
    private Claim() {
        this(UUID.randomUUID(), null);
    }
    
    /**
     * Get the user who created this claim.
     * @return The user's UUID.
     */
    public UUID getUser() {
        return this.user;
    }
    
    /**
     * Set the user who created this claim.
     * @param user The user's UUID.
     */
    public void setUser(UUID user) {
        this.user = user;
        this.<Claim>hasChanged(this);
    }
    
    /**
     * Get the approver who first approved/returned this claim.
     * @return The approver's UUID, or null if no approver.
     */
    public UUID getApprover() {
        return this.approver;
    }
    
    /**
     * Get the approver who first approved/returned this claim.
     * @param approver The approver's UUID.
     */
    public void setApprover(UUID approver) {
        this.approver = approver;
        this.<Claim>hasChanged(this);
    }
    
    /**
     * Get the claim's status.
     * @return The status.
     */
    public Status getStatus() {
        return status;
    }
    
    /**
     * Set the claim's status.
     * @param status The status.
     */
    public void setStatus(Status status) {
        this.status = status;
        this.<Claim>hasChanged(this);
    }

    /**
     * Get the claim's start date.
     * @return The start date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the claim's start date.
     * @param startDate The start date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        this.<Claim>hasChanged(this);
    }

    /**
     * Get the claim's end date.
     * @return The end date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the claim's end date.
     * @param endDate The end date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        this.<Claim>hasChanged(this);
    }

    /**
     * Get the list of destinations.
     * @return The list of destinations.
     */
    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    /**
     * Set the list of destinations.
     * Note that this should be called rather than directly editing
     * the result of getDestinations() so that observers are notified.
     * 
     * @param destinations The list of destinations.
     */
    public void setDestinations(ArrayList<Destination> destinations) {
        this.destinations = destinations;
        this.<Claim>hasChanged(this);
    }

    /**
     * Get the list of approver comments.
     * @return The list of approver comments.
     */
    public ArrayList<ApproverComment> getComments() {
        return comments;
    }

    /**
     * Set the list of approver comments.
     * Note that this should be called rather than directly editing
     * the result of getComments() so that observers are notified.
     * 
     * @param comments The list of comments.
     */
    public void setComments(ArrayList<ApproverComment> comments) {
        this.comments = comments;
        this.<Claim>hasChanged(this);
    }
    
    /**
     * Add a premade comment to the claim.
     * @param comment The comment to add.
     */
    public void addComment(ApproverComment comment) {
        this.comments.add(comment);
        this.<Claim>hasChanged(this);
    }
    
    /**
     * Add a comment to the start of the claim's list with today's date.
     * @param commentText The comment's text.
     */
    public void addComment(String commentText) {
        this.comments.add(0, new ApproverComment(commentText, new Date()));
        this.<Claim>hasChanged(this);
    }

    /**
     * Get the list of tags.
     * @return The list of tags.
     */
    public ArrayList<UUID> getTags() {
        return this.tags;
    }

    /**
     * Set the list of tags.
     * Note that this should be called rather than directly editing
     * the result of getTags() so that observers are notified.
     * 
     * @param tags The list of tags.
     */
    public void setTags(ArrayList<UUID> tags) {
        this.tags = tags;
        this.<Claim>hasChanged(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((approver == null) ? 0 : approver.hashCode());
        result = prime * result
                + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result
                + ((destinations == null) ? 0 : destinations.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof Claim))
            return false;
        Claim other = (Claim) obj;
        if (approver == null) {
            if (other.approver != null)
                return false;
        } else if (!approver.equals(other.approver))
            return false;
        if (comments == null) {
            if (other.comments != null)
                return false;
        } else if (!comments.equals(other.comments))
            return false;
        if (destinations == null) {
            if (other.destinations != null)
                return false;
        } else if (!destinations.equals(other.destinations))
            return false;
        if (status != other.status)
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    protected boolean mergeFrom(Document sourceDoc) {
        if (!(sourceDoc instanceof Claim))
            return false;
        Claim sourceClaim = (Claim) sourceDoc;
        boolean changed = false;
        
        if (this.approver != null) {
            if (!this.approver.equals(sourceClaim.approver)) {
                changed |= true;
                this.approver = sourceClaim.approver;
            }
        } else {
            // attribute is null, if source is not null then set it
            if (!(sourceClaim.approver == null)) {
                changed |= true;
                this.approver = sourceClaim.approver;
            }
        }
        
        if (this.comments != null) {
            if (!this.comments.equals(sourceClaim.comments)) {
                changed |= true;
                this.comments = sourceClaim.comments;
            }
        } else {
            // attribute is null, if source is not null then set it
            if (!(sourceClaim.comments == null)) {
                changed |= true;
                this.comments = sourceClaim.comments;
            }
        }
        
        if (this.destinations != null) {
            if (!this.destinations.equals(sourceClaim.destinations)) {
                changed |= true;
                this.destinations = sourceClaim.destinations;
            }
        } else {
            // attribute is null, if source is not null then set it
            if (!(sourceClaim.destinations == null)) {
                changed |= true;
                this.destinations = sourceClaim.destinations;
            }
        }
        
        if (this.endDate != null) {
            if (!this.endDate.equals(sourceClaim.endDate)) {
                changed |= true;
                this.endDate = sourceClaim.endDate;
            }
        } else {
            // attribute is null, if source is not null then set it
            if (!(sourceClaim.endDate == null)) {
                changed |= true;
                this.endDate = sourceClaim.endDate;
            }
        }
        
        if (this.startDate != null) {
            if (!this.startDate.equals(sourceClaim.startDate)) {
                changed |= true;
                this.startDate = sourceClaim.startDate;
            }
        } else {
            // attribute is null, if source is not null then set it
            if (!(sourceClaim.startDate == null)) {
                changed |= true;
                this.startDate = sourceClaim.startDate;
            }
        }
        
        if (this.status != null) {
            if (!this.status.equals(sourceClaim.status)) {
                changed |= true;
                this.status = sourceClaim.status;
            }
        } else {
            // attribute is null, if source is not null then set it
            if (!(sourceClaim.status == null)) {
                changed |= true;
                this.status = sourceClaim.status;
            }
        }
        
        if (this.tags != null) {
            if (!this.tags.equals(sourceClaim.tags)) {
                changed |= true;
                this.tags = sourceClaim.tags;
            }
        } else {
            // attribute is null, if source is not null then set it
            if (!(sourceClaim.tags == null)) {
                changed |= true;
                this.tags = sourceClaim.tags;
            }
        }
        
        if (this.user != null) {
            if (!this.user.equals(sourceClaim.user)) {
                changed |= true;
                this.user = sourceClaim.user;
            }
        } else {
            // attribute is null, if source is not null then set it
            if (!(sourceClaim.user == null)) {
                changed |= true;
                this.user = sourceClaim.user;
            }
        }
        
        return changed;
    }
    
}
