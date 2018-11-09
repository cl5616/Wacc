#include "../waccc.h"

#include <stack>
#include <vector>
class context
{
private:
	std::stack<std::vector<u4>*> contexts;
public:
	void newStackFrame(u2 num);
	void newStackFrame(std::vector<u4>& v);
	void prevStackFrame();
	u2 getBlockNum();
	u4& operator[](std::size_t idx);
};