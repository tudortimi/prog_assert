// Copyright 2015 Tudor Timisescu (verificationgentleman.com)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


`include "prog_assert.svh"


class rectangle;
  extern function new(int unsigned side0, int unsigned side1);
  extern function int unsigned get_area();

  protected const int unsigned side0, side1;
endclass


function rectangle::new(int unsigned side0, int unsigned side1);
  `prog_assert(side0 > 0)
  `prog_assert(side1 > 0)
  this.side0 = side0;
  this.side1 = side1;
endfunction

function int unsigned rectangle::get_area();
  return side0 * side1;
endfunction
