// Freckles.cpp : Defines the entry point for the console application.
//

#include "math.h"
#include <vector>
#include <queue>
#include <iostream> //cin and cout
#include <iomanip>

using namespace std;

//Suggested data structures, I actually love these! :)
typedef pair<double, double> ii;
typedef vector<double> vi;
typedef vector<ii> vii;
typedef pair<double, ii> doublePair;


//Need to declare functions ahead of time here in c++ it seems.
double distanceCalculation(ii point1, ii point2);
int frecklesSolver(vii adjList);

//From competitive programming 3. Union find function.
// Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
class UnionFind
{                                              // OOP style
private:
	vi p, rank, setSize;                       // remember: vi is vector<int>
	int numSets;
public:
	UnionFind(int N)
	{
		setSize.assign(N, 1); numSets = N; rank.assign(N, 0);
		p.assign(N, 0); for (int i = 0; i < N; i++) p[i] = i;
	}
	int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }
	bool isSameSet(int i, int j) { return findSet(i) == findSet(j); }
	void unionSet(int i, int j)
	{
		if (!isSameSet(i, j))
		{
			numSets--;
			int x = findSet(i), y = findSet(j);
			// rank is used to keep the tree short
			if (rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
			else
			{
				p[x] = y; setSize[y] += setSize[x];
				if (rank[x] == rank[y]) rank[y]++;
			}
		}
	}
	int numDisjointSets() { return numSets; }
	int sizeOfSet(int i) { return setSize[findSet(i)]; }
};


int main()
{
	freopen("inOne.txt", "r", stdin);
	vii adjList; //Initialize Adjacency List 
	int testCaseCount;

	cin >> testCaseCount;
	for (int i = 0; i < testCaseCount; i++)
	{

		//Step 1: Read input and create nodes with the data. Assign node "identity" by index.
		//Eg: Node 1 = adjList[0], Node 2 = adjList[1], etc.
		int nodeCount;
		cin >> nodeCount; //Get count of how many nodes I'm dealing with.
		
		
		for (int i = 0; i < nodeCount; i++) //Obtain all lines from stdin.
		{
			double f1, f2;
			cin >> f1 >> f2;
			//cout << f1 << " " << f2 << endl; //Test to ensure input is correct. 
			adjList.push_back(ii{ f1, f2 });

		}
		frecklesSolver(adjList);
		adjList.clear();
	}
	
    return 0;
}

int frecklesSolver(vii adjList)
{

	vii nodePairsList; //Initialize List of Node Pairs Eg: <Node1, Node2> 
	vector<pair<double, ii>> EdgeList; 	//This is going to be used like <Weight of pair, Node pair>

	for (int firstNode = 0; firstNode < adjList.size(); firstNode++)
	{
		for (int secondNode = firstNode + 1; secondNode < adjList.size(); secondNode++)
		{
			EdgeList.push_back(doublePair(distanceCalculation(adjList[firstNode], adjList[secondNode]), ii(firstNode,secondNode)));

		}
	}


	//From Competitive Programming 3.
	sort(EdgeList.begin(), EdgeList.end()); // sort by edge weight O(E log E)
											// note: pair object has built-in comparison function
	double mst_cost = 0;
	UnionFind UF(adjList.size()); // all V are disjoint sets initially
	for (int i = 0; i < EdgeList.size(); i++)
	{ // for each edge, O(E)
		pair<double, ii> front = EdgeList[i];
		if (!UF.isSameSet(front.second.first, front.second.second))
		{ // check
			mst_cost += front.first; // add the weight of e to MST
			UF.unionSet(front.second.first, front.second.second); // link them
		}
	} // note: the runtime cost of UFDS is very light
	  // note: the number of disjoint sets must eventually be 1 for a valid MST
	cout << fixed << setprecision(2) << mst_cost << endl;

	return 0;
}
//Obtains two points and then will calculate the distance between them.
//Used to get the "weight" of the nodes so that I can run the rest of the MST algorithms.
//Formula for distance obtained from: http://www.mathwarehouse.com/calculators/distance-formula-calculator.php
double distanceCalculation(ii point1, ii point2)
{
	return sqrt(pow((point2.first - point1.first), 2.0) + pow((point2.second - point1.second), 2.0));
}


