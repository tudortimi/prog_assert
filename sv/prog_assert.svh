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


`ifndef __PROG_ASSERT
`define __PROG_ASSERT


`ifdef NDEBUG
  `define prog_assert(expr) \
    begin \
    end
`else
  `define prog_assert(expr) \
    begin \
      if (!(expr)) begin \
`ifdef INCA \
        $stacktrace; \
`endif \
        $fatal(0, $sformatf("Assertion '%s' failed.", `"expr`")); \
      end \
    end
`endif


`ifdef NDEBUG
  `define prog_verify(expr) \
    begin \
      void'(expr); \
    end
`else
  `define prog_verify(expr) \
    `prog_assert(expr)
`endif


`endif
