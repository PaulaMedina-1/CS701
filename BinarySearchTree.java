
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree( )
	{
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * @param x the item to insert.
	 */

	//ATTENTION: BOOLEAN VALUE IS NECESSARY TO OVERLOAD THE METHOD INSERT
	public void insert(AnyType x, boolean value) 
	{	// creating a binary node with item.element = x
		BinaryNode<AnyType> item = new BinaryNode<AnyType>(x);
		// check if our tree is empty
		if (root == null) {
			root = item;
			return;
		}
		BinaryNode<AnyType> parent = null;
		BinaryNode<AnyType> current = root;
		
		while (current != null) {
			parent = current;
			if (current.element.compareTo(x) <= 0) {
				current = current.right;
			}
			else {
				current = current.left;
			}
		}
		
		if (parent.element.compareTo(x) <= 0 ) {
			parent.right = item;
		}
		else {
			parent.left = item;
		}
		return;
	}
	
	public boolean contains(AnyType x, boolean guess) {
		
		BinaryNode<AnyType> item = new BinaryNode<AnyType>(x);
		
		if ( root == null) {
			return false;
		}
		
		BinaryNode<AnyType> current = root;
		while(current != null) {
			if (current.element.compareTo(x) >= 1) {
				if (current.left != null && current.left.element == item) {
					return true;
				}
			
				current = current.left;
			}
			else {
				
				if (current.right != null && current.right.element == item) {
					return true;
				}
				current = current.right;
			}
		}
		// BinaryNode is not found if we went through the tree and got to this line
		return false;
	}

	public void insert( AnyType x )
	{
		root = insert( x, root );
	}

	
	public void remove( AnyType x )
	{
		root = remove( x, root );
	}

	
	public AnyType findMin( )
	{
		if( isEmpty( ) )
			throw new UnderFlowException( );
		return findMin( root ).element;
	}

	
	public AnyType findMax( )
	{
		if( isEmpty( ) )
			throw new UnderFlowException( );
		return findMax( root ).element;
	}

	
	public boolean contains( AnyType x )
	{
		return contains( x, root );
	}

	
	public void makeEmpty( )
	{
		root = null;
	}

	
	public boolean isEmpty( )
	{
		return root == null;
	}

	
	public void printTree( )
	{
		if( isEmpty( ) )
			System.out.println( "Empty tree" );
		else
			printTree( root );
	}

	
	 
	private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return new BinaryNode<AnyType>( x, null, null );

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			t.left = insert( x, t.left );
		else if( compareResult > 0 )
			t.right = insert( x, t.right );
		else
			;  // Duplicate; do nothing
		return t;
	}

	
	private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return t;   // Item not found; do nothing

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			t.left = remove( x, t.left );
		else if( compareResult > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // Two children
		{
			t.element = findMin( t.right ).element;
			t.right = remove( t.element, t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right;
		return t;
	}

	
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
	{
		if( t == null )
			return null;
		else if( t.left == null )
			return t;
		return findMin( t.left );
	}

	
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
	{
		if( t != null )
			while( t.right != null )
				t = t.right;

		return t;
	}

	
	private boolean contains( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return false;

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			return contains( x, t.left );
		else if( compareResult > 0 )
			return contains( x, t.right );
		else
			return true;    // Match
	}

	
	private void printTree( BinaryNode<AnyType> t )
	{
		if( t != null )
		{
			printTree( t.left );
			System.out.println( t.element );
			printTree( t.right );
		}
	}

	
	private int height( BinaryNode<AnyType> t )
	{
		if( t == null )
			return -1;
		else
			return 1 + Math.max( height( t.left ), height( t.right ) );    
	}

	
	private static class BinaryNode<AnyType>
	{
		
		BinaryNode( AnyType theElement )
		{
			this( theElement, null, null );
		}

		BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
		{
			element  = theElement;
			left     = lt;
			right    = rt;
		}

		AnyType element;            // The data in the node
		BinaryNode<AnyType> left;   // Left child
		BinaryNode<AnyType> right;  // Right child
	}


	/** The tree root. */
	private BinaryNode<AnyType> root;



	
}
