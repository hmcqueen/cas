package org.jasig.cas.server.session;

import org.jasig.cas.server.login.TokenServiceAccessRequest;

/**
 * Represents a request to access a resource.  Implementations of this interface would be specific versions of how to
 * access that resource, i.e. either via the CAS1 or CAS2 protocol, etc.
 *
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.5
 */
public interface Access {

    /**
     * Returns the unique identifier for this Access.
     *
     * @return the unique identifier for this access.  CANNOT be null.
     */
    String getId();

    /**
     * The identifier for this resource, i.e. the URL.
     *
     * @return the identifier for the resource.  CANNOT be null.
     */
    String getResourceIdentifier();

    /**
     * Validates the token request against this specific access.  A Validation request should modify the internal
     * state of the Access such that when the system next asks for an appropriate response to give to the calling/client
     * application, it can.
     * <p>
     * Examples:
     * <ul>
     * <li> A self-validating token may not modify the state of the system at all.</li>
     * <li> A CAS implementation may modify some internal state, i.e. number of uses, etc.</li>
     * </ul>
     * @param tokenServiceAccessRequest the request for validation.
     */
    void validate(TokenServiceAccessRequest tokenServiceAccessRequest);

    /**
     * Notifies the existing system that the user's local session should be destroyed.
     *
     * @return true if the session was able to be destroyed, false otherwise.
     */
    boolean invalidate();

    /**
     * Determines if the local session was destroyed or not.
     *
     * @return true if the local session was destroyed, false otherwise.
     */
    boolean isLocalSessionDestroyed();

    /**
     * Denotes whether the Access needs to be remembered or not.  If it does not support logout and does not need
     * validation then it may not require storage.
     *
     * @return true if it requires storage, false otherwise.
     */
    boolean requiresStorage();

    /**
     * Generates the response to send back to the client.
     *
     * @param accessResponseRequest the request for a response.
     * @return the result of the request.  CANNOT be null.
     */
    AccessResponseResult generateResponse(AccessResponseRequest accessResponseRequest);
}
