#include "context.h"

using namespace std;
void context::newStackFrame(u2 num)
{
	contexts.push(new vector<u4>(num));
}

void context::newStackFrame(std::vector<u4>& v)
{
	contexts.push(new vector<u4>(v));
}
void context::prevStackFrame()
{
	delete contexts.top();
	contexts.pop();
}

u4& context::operator[](size_t idx)
{
	return (*contexts.top())[idx];
}

u2 context::getBlockNum()
{
	return (u2)contexts.top()->size();
}