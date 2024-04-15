clear all

x0 = [1;1];

function f = func(x)
    f = max((x(1) - 1)^2, x(1)^2-4*(x(2) - 1)^2);
end

op = optimset('PlotFcns',@optimplotfval);

[x,fval,exitflag,output] = fminsearch(@func,x0,op)
